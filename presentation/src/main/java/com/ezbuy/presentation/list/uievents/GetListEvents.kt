package com.ezbuy.presentation.list.uievents

import com.ezbuy.domain.model.list.ListModel
import java.io.IOException

sealed interface GetListEvents {
    object Idle : GetListEvents
    data class Success(val listData: ListModel) : GetListEvents
    data class Failure(val error: IOException) : GetListEvents
}