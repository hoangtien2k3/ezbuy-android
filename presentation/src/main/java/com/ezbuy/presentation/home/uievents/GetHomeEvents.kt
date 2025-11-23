package com.ezbuy.presentation.home.uievents

import com.ezbuy.domain.model.home.HomeModel
import java.io.IOException

sealed interface GetHomeEvents {
    object Idle : GetHomeEvents
    object StartShimmer : GetHomeEvents

    data class Success(val homeData: HomeModel) : GetHomeEvents

    data class Failure(val error: IOException) : GetHomeEvents
}
