package com.ezbuy.app.common

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.view.View
import android.view.Window
import android.widget.TextView
import androidx.core.graphics.drawable.toDrawable
import com.ezbuy.app.R

object LoadingScreen {
    var dialog: Dialog? = null

    fun displayLoadingWithText(
        context: Context?,
        text: String?,
        cancelable: Boolean,
    ) {
        dialog = Dialog(context!!)
        dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog!!.setContentView(R.layout.layout_loading_screen)
        dialog!!.window!!.setBackgroundDrawable(Color.TRANSPARENT.toDrawable())
        dialog!!.setCancelable(cancelable)
        val textView = dialog!!.findViewById<TextView>(R.id.text)
        textView.text = text
        try {
            dialog!!.show()
        } catch (_: Exception) {
        }
    }

    fun displayLoading(
        context: Context?,
        cancelable: Boolean,
    ) {
        dialog = Dialog(context!!)
        dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog!!.setContentView(R.layout.layout_loading_screen)
        dialog!!.window!!.setBackgroundDrawable(Color.TRANSPARENT.toDrawable())
        dialog!!.setCancelable(cancelable)
        val textView = dialog!!.findViewById<TextView>(R.id.text)
        textView.visibility = View.GONE
        try {
            dialog!!.show()
        } catch (_: Exception) {
        }
    }

    fun hideLoading() {
        try {
            if (dialog != null) {
                dialog!!.dismiss()
            }
        } catch (_: Exception) {
        }
    }
}
