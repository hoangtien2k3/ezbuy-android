package com.ezbuy.domain.usecase

import com.ezbuy.common.utils.Resource
import com.ezbuy.domain.model.home.HomeModel

interface GetHomeUseCase {
    suspend operator fun invoke(): Resource<HomeModel>
}
