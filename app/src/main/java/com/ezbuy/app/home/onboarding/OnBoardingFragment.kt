package com.ezbuy.app.home.onboarding

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ezbuy.app.R
import com.ezbuy.app.common.viewBinding
import com.ezbuy.app.databinding.FragmentOnBoardingBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnBoardingFragment : Fragment(R.layout.fragment_on_boarding) {
    private val binding by viewBinding(FragmentOnBoardingBinding::bind)

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            startBtn.setOnClickListener {
                val action = OnBoardingFragmentDirections.actionOnBoardingFragmentToSignInWithPassword()
                findNavController().navigate(action)
            }
//      signupStart.setOnClickListener {
//        val signupAction = OnBoardingFragmentDirections.actionOnBoardingFragmentToSignInWithPassword()
//        findNavController().navigate(signupAction)
//      }
        }
    }
}
