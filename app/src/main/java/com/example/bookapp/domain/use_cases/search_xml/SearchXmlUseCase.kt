package com.example.bookapp.domain.use_cases.search_xml

import androidx.paging.PagingData
import com.example.bookapp.data.repository.Repository
import com.example.bookapp.domain.model.XmlModel
import kotlinx.coroutines.flow.Flow

class SearchXmlUseCase(
    private val repository: Repository
) {
    operator fun invoke(query: String): Flow<PagingData<XmlModel>> {
        return repository.searchXml(query = query)
    }
}