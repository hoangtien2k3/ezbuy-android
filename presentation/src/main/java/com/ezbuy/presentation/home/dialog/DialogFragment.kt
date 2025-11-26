package com.ezbuy.presentation.home.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.LinearLayout
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.ezbuy.presentation.R
import com.ezbuy.presentation.common.viewBinding
import com.ezbuy.presentation.databinding.FragmentDialogBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DialogFragment : BottomSheetDialogFragment(R.layout.fragment_dialog) {
    private val binding by viewBinding(FragmentDialogBinding::bind)

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog = Dialog(requireContext(), R.style.AppEzbuyStyle)

    override fun onStart() {
        super.onStart()

        val window = dialog?.window
        dialog?.setCancelable(false)
        window?.setLayout(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT,
        )

        window?.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        context?.let {
            if (window != null) {
                window.statusBarColor = it.getColor(R.color.transparent)
            }
        }
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)

        val agrsDialog: DialogArguments? = arguments?.getParcelable("DialogKey")

        with(binding) {
            title.text = agrsDialog?.title.orEmpty()
            message.text = agrsDialog?.message.orEmpty()
            image.setImageResource(agrsDialog?.imageResource ?: 0)

            lifecycleScope.launch {
                delay(5000)
                findNavController().navigate(agrsDialog?.navigationDestination ?: 0)
            }
        }
    }

    companion object {
        fun newInstance(dialogAgrument: DialogArguments): DialogFragment {
            return DialogFragment().apply {
                this.arguments =
                    Bundle().apply {
                        putParcelable("DialogKey", dialogAgrument)
                    }
            }
        }
    }
}
