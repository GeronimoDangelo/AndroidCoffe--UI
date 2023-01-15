package com.coffee.bookapp.domain.use_cases.get_selected_xmls

import com.coffee.bookapp.data.repository.Repository
import com.coffee.bookapp.domain.model.XmlModel

class GetSelectXmlUseCase(
    private val repository: Repository
) {
   suspend operator fun invoke(xmlId: Int) : XmlModel {
       return  repository.getSelectedXml(xmlId = xmlId)
   }
}