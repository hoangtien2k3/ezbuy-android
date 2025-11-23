package com.ezbuy.presentation.common.extension

import android.content.Intent
import androidx.fragment.app.Fragment
import com.ezbuy.presentation.detail.DetailFragment

fun Fragment.shareLink(
    link: String?,
    title: String,
) {
    val shareIntent = Intent(Intent.ACTION_SEND)
    shareIntent.type = DetailFragment.INTENT_SHARE
    shareIntent.putExtra(Intent.EXTRA_TEXT, link)
    startActivity(Intent.createChooser(shareIntent, title))
}
