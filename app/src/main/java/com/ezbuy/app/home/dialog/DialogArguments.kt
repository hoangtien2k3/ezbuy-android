package com.ezbuy.app.home.dialog

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DialogArguments(
    val title: String,
    val message: String,
    val imageResource: Int,
    val navigationDestination: Int,
) : Parcelable
