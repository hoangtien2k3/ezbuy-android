package com.ezbuy.app.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.viewbinding.ViewBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

open class BaseBottomSheetDialog<VB : ViewBinding>(private val inflate: (LayoutInflater, ViewGroup?, Boolean) -> VB) :
  BottomSheetDialogFragment() {

  private var _binding: VB? = null
  val binding get() = _binding!!

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
    inflate(
      inflater,
      container,
      false,
    ).also { _binding = it }.root

  @CallSuper
  override fun onDestroyView() {
    _binding = null
    super.onDestroyView()
  }
}
