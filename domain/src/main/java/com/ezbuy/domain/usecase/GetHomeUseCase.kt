package com.ezbuy.domain.usecase

import com.ezbuy.common.utils.Resource
import com.ezbuy.domain.model.home.HomeModel
import com.ezbuy.domain.repository.HomeRepository
import javax.inject.Inject

class GetHomeUseCase @Inject constructor(
    private val homeRepository: HomeRepository
) {
    suspend operator fun invoke(): Resource<HomeModel> {
        return homeRepository.getHome()
    }
}
