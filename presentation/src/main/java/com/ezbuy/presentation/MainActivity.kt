package com.ezbuy.presentation

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.ezbuy.data.network.NetworkMonitor
import com.ezbuy.presentation.base.BaseActivity
import com.ezbuy.presentation.common.hideWithoutAnimation
import com.ezbuy.presentation.common.launchAndRepeatStarted
import com.ezbuy.presentation.common.showWithAnimation
import com.ezbuy.presentation.common.toast
import com.ezbuy.presentation.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val viewModel by viewModels<MainViewModel>()

    private lateinit var navController: NavController

    @Inject
    internal lateinit var networkMonitor: NetworkMonitor

    override fun onInflateView(inflater: LayoutInflater): ActivityMainBinding = ActivityMainBinding.inflate(inflater)

    override fun setupData(savedInstanceState: Bundle?) {
        if (savedInstanceState === null) {
            setupBottomNavController()
        }
        launchAndRepeatStarted { networkMonitor.isOnline.collect(::handleOnlineStatus) }
    }

    private fun handleOnlineStatus(isOnline: Boolean) {
        if (!isOnline) {
            toast("Network is disconnected...")
        } else {
            toast("Network is connected...")
        }
    }

    private fun setupBottomNavController() {
        val navHostFragment = binding.navHostContainer.getFragment<NavHostFragment>()
        navController = navHostFragment.navController

        navHostFragment.navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.onBoardingFragment -> {
                    binding.bottomNavView.hideWithoutAnimation(binding.navHostContainer)
                }

                R.id.splashScreenFragment,
                R.id.dialogFragment,
                R.id.signInWithPassword, -> {
                    binding.bottomNavView.hideWithoutAnimation(binding.navHostContainer)
                }

                else -> {
                    binding.bottomNavView.showWithAnimation(binding.navHostContainer)
                }
            }
        }

//    binding.bottomNavView.setupWithNavController(navController)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        setupBottomNavController()
    }
}
