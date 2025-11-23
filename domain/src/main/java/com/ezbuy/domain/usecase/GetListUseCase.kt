package com.ezbuy.domain.usecase

import com.ezbuy.common.utils.Resource
import com.ezbuy.domain.model.list.ListModel
import com.ezbuy.domain.model.list.ListRequestModel

interface GetListUseCase {
    suspend operator fun invoke(params: ListRequestModel): Resource<ListModel>
}
