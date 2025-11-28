package com.ezbuy.app.compose.uievent

sealed class UIEvent {
    data class OnToastShow(val productName: String) : UIEvent()
}
