package com.ezbuy.common.extensions

import com.ezbuy.common.model.DomainModel
import com.ezbuy.common.model.ResponseModel

interface DataToDomainModelMapper<R : ResponseModel, U : DomainModel> {
    fun mapToDomainModel(responseModel: R?): U?
}
