package com.ezbuy.app.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.ezbuy.app.common.ErrorPopupFragment
import com.ezbuy.app.common.extension.hide
import com.ezbuy.app.common.extension.show
import com.ezbuy.app.home.uievents.GetHomeEvents
import com.ezbuy.app.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private val viewModel: HomeSharedViewModel by activityViewModels()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var navController: NavController

    private val fragmentPopup = ErrorPopupFragment()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding =
            FragmentHomeBinding.inflate(
                inflater,
                container,
                false,
            )
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)
        initVMObservers()

        findNavController().let {
            navController = it
        }
    }

    private fun initVMObservers() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.homeFlow.collect {
                        when (it) {
                            is GetHomeEvents.StartShimmer -> {
                                binding.shimmerViewContainer.show()
                            }

                            is GetHomeEvents.Success -> {
                                binding.shimmerViewContainer.hide()
                                bindUI()
                            }

                            is GetHomeEvents.Failure -> {
                                fragmentPopup.show(
                                    requireActivity().supportFragmentManager,
                                    ERROR_POP_UP_FRAGMENT_TAG,
                                )
                            }

                            else -> Unit
                        }
                    }
                }
            }
        }
    }

    private fun bindUI() {
        bindRecyclerView()
        routeActions()
    }

    private fun bindRecyclerView() {

    }

    private fun routeActions() {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val ERROR_POP_UP_FRAGMENT_TAG = "error_popup_fragment"
    }
}
