package com.ezbuy.presentation.common

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.drawable.Drawable
import android.util.TypedValue
import android.view.View
import android.widget.EditText
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.ezbuy.presentation.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import www.sanju.motiontoast.MotionToast
import www.sanju.motiontoast.MotionToastStyle
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.random.Random
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import android.widget.Toast

fun EditText.changeFocusedInputTint(isFocused: Boolean) {
    if (isFocused) {
        for (drawable in this.compoundDrawables) {
            if (drawable != null) {
                drawable.colorFilter =
                    PorterDuffColorFilter(
                        ContextCompat.getColor(
                            this.context,
                            R.color.text_color,
                        ),
                        PorterDuff.Mode.SRC_IN,
                    )
            }
        }
    } else {
        if (this.text.toString().isEmpty()) {
            for (drawable in this.compoundDrawables) {
                if (drawable != null) {
                    drawable.colorFilter =
                        PorterDuffColorFilter(
                            ContextCompat.getColor(
                                this.context,
                                R.color.inactive_input,
                            ),
                            PorterDuff.Mode.SRC_IN,
                        )
                }
            }
        }
    }
}

fun getRandomRuntime(): Int = Random.nextInt(90, 180)

fun getRandomDownloadSize(): Double = Random.nextDouble(500.0, 3000.0)

fun Double.convertMBtoGB(addText: Boolean): String {
    if (this >= 1024.0) {
        return "${(this / 1024.0).format(1)} ${if (addText) " GB" else ""}"
    }

    return "${this.format(1)} ${if (addText) " MB" else ""}"
}

fun Int.formatTime(): String {
    val hours = this / 60
    val remainingMinutes = this % 60
    return String.format(Locale.getDefault(), "%01dh %02dm", hours, remainingMinutes)
}

fun Double.format(digits: Int): String {
    val df = DecimalFormat()
    df.decimalFormatSymbols = DecimalFormatSymbols(Locale.US)
    df.maximumFractionDigits = digits
    return df.format(this)
}

fun getReformatDate(dateInString: String?): String =
    if (dateInString != null) {
        val parser = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val formatter = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
        try {
            val date = parser.parse(dateInString)
            formatter.format(date!!)
        } catch (e: ParseException) {
            "-"
        }
    } else {
        "-"
    }

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.animateTranslationY(
    animateFrom: Float,
    animateTo: Float,
    duration: Long,
) {
    val animator =
        ObjectAnimator.ofFloat(
            this,
            "translationY",
            TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                animateTo,
                resources.displayMetrics,
            ),
            TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                animateFrom,
                resources.displayMetrics,
            ),
        )
    animator.duration = duration
    if (animateTo == 0f) {
        animator.addListener(
            object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    this@animateTranslationY.gone()
                }
            },
        )
    }
    animator.start()
}

fun View.animateMarginBottom(
    size: Float,
    duration: Long,
) {
    val dpToPx =
        TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            size,
            resources.displayMetrics,
        )

    val params =
        this.layoutParams as ConstraintLayout.LayoutParams
    val animator = ValueAnimator.ofInt(params.bottomMargin, dpToPx.toInt())
    animator.addUpdateListener {
        val value = it.animatedValue as Int
        params.setMargins(
            params.leftMargin,
            params.topMargin,
            params.rightMargin,
            value,
        )
        this.layoutParams = params
    }
    animator.duration = duration
    animator.start()
}

fun BottomNavigationView.showWithAnimation(fragmentContainerView: View) {
    if (this.isVisible) return
    this.visible()
    this.animateTranslationY(0f, 60f, 700)
    fragmentContainerView.animateMarginBottom(60f, 700)
}

fun BottomNavigationView.hideWithAnimation(fragmentContainerView: View) {
    if (this.isGone) return
    this.animateTranslationY(60f, 0f, 700)
    fragmentContainerView.animateMarginBottom(0f, 700)
}

fun BottomNavigationView.hideWithoutAnimation(fragmentContainerView: View) {
    if (this.isGone) return
    this.gone()

    val params =
        fragmentContainerView.layoutParams as ConstraintLayout.LayoutParams
    params.setMargins(
        params.leftMargin,
        params.topMargin,
        params.rightMargin,
        0,
    )
    fragmentContainerView.layoutParams = params
}

fun Activity.showToast(
    title: String?,
    description: String,
    style: MotionToastStyle,
) {
    MotionToast.createColorToast(
        this,
        title,
        description,
        style,
        MotionToast.GRAVITY_TOP or MotionToast.GRAVITY_CENTER,
        MotionToast.LONG_DURATION,
        ResourcesCompat.getFont(this, R.font.urbanist_font_family),
    )
}

fun Context.circularProgressDrawable(): Drawable =
    CircularProgressDrawable(this).apply {
        strokeWidth = 7f
        centerRadius = 60f
        setColorSchemeColors(
            ContextCompat.getColor(
                this@circularProgressDrawable,
                R.color.text_color,
            ),
        )
        start()
    }

fun Context.openShareIntent(text: String) {
    val intent = Intent(Intent.ACTION_SEND)
    intent.type = "text/plain"
    intent.putExtra(Intent.EXTRA_TEXT, text)
    startActivity(
        Intent.createChooser(intent, getString(R.string.share))
            .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK),
    )
}

// Extension function for Activity to launch coroutine with lifecycle awareness
fun LifecycleOwner.launchAndRepeatStarted(
  block: suspend CoroutineScope.() -> Unit,
) {
  lifecycleScope.launch {
    repeatOnLifecycle(Lifecycle.State.STARTED) {
      block()
    }
  }
}

// Extension function for Activity to show toast
fun Activity.toast(message: String) {
  Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}
