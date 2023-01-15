package com.coffee.bookapp.data.paging_source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.coffee.bookapp.data.remote.BookApi
import com.coffee.bookapp.domain.model.XmlModel

class SearchXmlSource(
    private val bookApi: BookApi,
    private val query:String
) : PagingSource<Int,XmlModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, XmlModel> {
        return try {
            val apiResponse = bookApi.searchXml(name = query)
            val xml = apiResponse.xml
            if (xml.isNotEmpty()) {
                LoadResult.Page(
                    data = xml,
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


    override fun getRefreshKey(state: PagingState<Int, XmlModel>): Int? {
        return state.anchorPosition
    }
}