package com.coffee.bookapp.data.paging_source


import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.coffee.bookapp.data.local.BookDatabase
import com.coffee.bookapp.data.remote.BookApi
import com.coffee.bookapp.domain.model.Book
import com.coffee.bookapp.domain.model.BookRemoteKeys
import com.coffee.bookapp.domain.model.Jetpack
import com.coffee.bookapp.domain.model.JetpackRemoteKeys
import javax.inject.Inject
import java.lang.Exception

@ExperimentalPagingApi
class JetpackRemoteMediator (
    private val bookApi: BookApi,
    private val bookDatabase: BookDatabase
) : RemoteMediator<Int, Jetpack>() {


    private val jetpackDao = bookDatabase.jetpackDao()
    private val jetpackRemoteKeysDao = bookDatabase.jetpackRemoteKeysDao()

    override suspend fun initialize(): InitializeAction {
        val currentTime = System.currentTimeMillis()
        val lastUpdated = jetpackRemoteKeysDao.getRemoteKeys(jetpackId = 1)?.lastUpdated ?: 0
        val cacheTimeout = 1440

        val diffInMinutes = (currentTime - lastUpdated) / 1000 / 60
        return if (diffInMinutes.toInt() <= cacheTimeout) {
            InitializeAction.SKIP_INITIAL_REFRESH
        } else {
            InitializeAction.LAUNCH_INITIAL_REFRESH
        }
    }

    override suspend fun load(loadType: LoadType, state: PagingState<Int, Jetpack>): MediatorResult {
        return try {
            val page = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextPage?.minus(1) ?: 1
                }
                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevPage = remoteKeys?.prevPage ?: return MediatorResult.Success(
                        endOfPaginationReached = remoteKeys != null
                    )
                    prevPage
                }
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextPage = remoteKeys?.nextPage ?: return MediatorResult.Success(
                        endOfPaginationReached = remoteKeys != null
                    )
                    nextPage
                }
            }


            val response = bookApi.getAllJetpacks(page = page)
            if (response.jetpacks.isNotEmpty()) {
                bookDatabase.withTransaction {
                    if (loadType == LoadType.REFRESH) {
                        jetpackDao.deleteAllJetpacks()
                        jetpackRemoteKeysDao.deleteAllRemoteKeys()
                    }
                    val prevPage = response.prevPage
                    val nextPage = response.nextPage
                    val keys = response.jetpacks.map { book ->
                        JetpackRemoteKeys(
                            id = book.id,
                            prevPage = prevPage,
                            nextPage = nextPage,
                            lastUpdated = response.lastUpdated
                        )
                    }
                    jetpackRemoteKeysDao.addAllRemoteKeys(jetpackRemoteKeys = keys)
                    jetpackDao.addJetpack(jetpacks = response.jetpacks)
                }
            }
            MediatorResult.Success(endOfPaginationReached = response.nextPage == null)

        } catch (e: Exception) {
            return MediatorResult.Error(e)
        }
    }


    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, Jetpack>
    ): JetpackRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                jetpackRemoteKeysDao.getRemoteKeys(jetpackId = id)
            }
        }
    }


    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, Jetpack>
    ): JetpackRemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { book ->
                jetpackRemoteKeysDao.getRemoteKeys(jetpackId = book.id)
            }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, Jetpack>): JetpackRemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { book ->
                jetpackRemoteKeysDao.getRemoteKeys(jetpackId = book.id)
            }
    }
}