package com.ezbuy.domain.usecase

import com.ezbuy.common.utils.Resource
import com.ezbuy.domain.model.detail.DetailModel

interface GetDetailUseCase {
    suspend operator fun invoke(): Resource<DetailModel>
}
