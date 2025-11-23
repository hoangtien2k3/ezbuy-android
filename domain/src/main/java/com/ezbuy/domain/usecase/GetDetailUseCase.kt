package com.ezbuy.domain.usecase

import com.ezbuy.domain.model.detail.DetailModel
import com.ezbuy.common.utils.Resource

interface GetDetailUseCase {
    suspend operator fun invoke(): Resource<DetailModel>
}
