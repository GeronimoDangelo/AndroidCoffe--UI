package com.example.bookapp.data.paging_source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.bookapp.data.remote.BookApi
import com.example.bookapp.domain.model.Book
import com.example.bookapp.domain.model.Jetpack

class SearchJetpackSource(
    private val bookApi: BookApi,
    private val query: String
) : PagingSource<Int, Jetpack>() {


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Jetpack> {
        return try {
            val apiResponse = bookApi.searchJetpack(name = query)
            val jets = apiResponse.jetpacks
            if (jets.isNotEmpty()) {
                LoadResult.Page(
                    data = jets,
                    prevKey = apiResponse.prevPage,
                    nextKey = apiResponse.nextPage
                )
            } else {
                LoadResult.Page(
                    data = emptyList(),
                    prevKey = null,
                    nextKey = null
                )
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    //
    //

    override fun getRefreshKey(state: PagingState<Int, Jetpack>): Int? {
        return state.anchorPosition
    }
}

