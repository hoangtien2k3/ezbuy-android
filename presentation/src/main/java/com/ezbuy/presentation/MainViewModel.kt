package com.ezbuy.presentation

import androidx.lifecycle.ViewModel
import com.ezbuy.common.dispatcher.AppCoroutineDispatchers
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel
    @Inject
    constructor(private val appCoroutineDispatchers: AppCoroutineDispatchers) : ViewModel()
