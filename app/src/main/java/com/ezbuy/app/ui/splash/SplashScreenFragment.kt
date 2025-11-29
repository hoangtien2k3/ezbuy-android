package com.ezbuy.app.ui.splash

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ezbuy.app.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashScreenFragment : Fragment(R.layout.fragment_splash_screen) {
    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    private var progressBarJob: Job? = null

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)

        val action = SplashScreenFragmentDirections.Companion.actionSplashScreenFragmentToOnBoardingFragment()
        progressBarJob =
            coroutineScope.launch {
                delay(2000)
                findNavController().navigate(action)
            }
    }

    override fun onDestroy() {
        super.onDestroy()
        progressBarJob?.cancel()
    }
}
