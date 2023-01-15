package com.coffee.bookapp.domain.use_cases.get_all_xml

import androidx.paging.PagingData
import com.coffee.bookapp.data.repository.Repository
import com.coffee.bookapp.domain.model.XmlModel
import kotlinx.coroutines.flow.Flow

class GetAllXmlUseCase(
    private val repository: Repository
) {
    operator fun  invoke () : Flow<PagingData<XmlModel>> {
        return repository.getAllXmls()
    }
}