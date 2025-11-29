package com.ezbuy.app.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.annotation.CallSuper
import androidx.annotation.MainThread
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.ezbuy.common.ui.safeNavigate

abstract class BaseFragment<VB : ViewBinding>(
    private val inflate: (LayoutInflater, ViewGroup?, Boolean) -> VB,
) : Fragment() {
    private var _binding: VB? = null
    protected val binding get() = _binding!!

    open var isEnableBackPress = true

    open var trackingClassName: String? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        requireActivity()
            .onBackPressedDispatcher
            .addCallback(
                this,
                object : OnBackPressedCallback(true) {
                    override fun handleOnBackPressed() {
                        if (isEnableBackPress) {
                            onBackPressed()
                        } else {
                            isEnabled = false
                            requireActivity().onBackPressedDispatcher.onBackPressed()
                        }
                    }
                },
            )
    }

    @CallSuper
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View =
        inflate(
            inflater,
            container,
            false,
        ).also { _binding = it }.root

    @CallSuper
    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        bindViewModel()
    }

    @MainThread
    protected abstract fun setupViews()

    @MainThread
    protected abstract fun bindViewModel()

    fun navigateTo() {
        findNavController().safeNavigate {
        }
    }

    fun popBackStack(
        id: Int? = null,
        isInclusive: Boolean? = null,
    ) {
        runCatching {
            if (id == null || isInclusive == null) {
                findNavController().popBackStack()
                return
            }
            findNavController().popBackStack(id, isInclusive)
        }
    }

    @CallSuper
    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    open fun onBackPressed() = Unit
}
