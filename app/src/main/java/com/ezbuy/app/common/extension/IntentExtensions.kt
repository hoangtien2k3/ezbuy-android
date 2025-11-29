package com.ezbuy.app.common.extension

import android.content.Intent
import androidx.fragment.app.Fragment

fun Fragment.shareLink(
    link: String?,
    title: String,
) {
    val shareIntent = Intent(Intent.ACTION_SEND)
    shareIntent.type = "text/plain"
    shareIntent.putExtra(Intent.EXTRA_TEXT, link)
    startActivity(Intent.createChooser(shareIntent, title))
}
