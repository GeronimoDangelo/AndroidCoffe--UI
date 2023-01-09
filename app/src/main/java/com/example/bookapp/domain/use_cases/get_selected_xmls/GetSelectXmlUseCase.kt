package com.example.bookapp.domain.use_cases.get_selected_xmls

import com.example.bookapp.data.repository.Repository
import com.example.bookapp.domain.model.XmlModel

class GetSelectXmlUseCase(
    private val repository: Repository
) {
   suspend operator fun invoke(xmlId: Int) : XmlModel {
       return  repository.getSelectedXml(xmlId = xmlId)
   }
}