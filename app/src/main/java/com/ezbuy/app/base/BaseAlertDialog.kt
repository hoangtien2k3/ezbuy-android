package com.ezbuy.app.base

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.viewbinding.ViewBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

abstract class BaseAlertDialog<B : ViewBinding> : DialogFragment() {

  private var viewBinding: B? = null

  protected val binding: B
    get() = checkNotNull(viewBinding)

  final override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
    val binding = onInflateView(layoutInflater)
    viewBinding = binding
    return MaterialAlertDialogBuilder(requireContext(), theme)
      .setView(binding.root)
      .create()
      .also(::onBuildDialog)
  }

  final override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
    viewBinding?.root

  open fun onBuildDialog(builder: AlertDialog) = Unit

  protected abstract fun onInflateView(inflater: LayoutInflater): B

  @CallSuper
  override fun onDestroyView() {
    viewBinding = null
    super.onDestroyView()
  }
}
