package com.example.bookapp.domain.use_cases.get_all_xml

import androidx.paging.PagingData
import com.example.bookapp.data.repository.Repository
import com.example.bookapp.domain.model.XmlModel
import kotlinx.coroutines.flow.Flow

class GetAllXmlUseCase(
    private val repository: Repository
) {
    operator fun  invoke () : Flow<PagingData<XmlModel>> {
        return repository.getAllXmls()
    }
}