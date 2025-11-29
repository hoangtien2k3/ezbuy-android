package com.ezbuy.app.ui

import androidx.lifecycle.ViewModel
import com.ezbuy.app.ui.uievents.GetHomeEvents
import com.ezbuy.domain.model.home.HomeSectionAdapterItem
import com.ezbuy.domain.usecase.GetHomeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class HomeSharedViewModel
    @Inject
    constructor(
        private val getHomeUseCase: GetHomeUseCase,
    ) : ViewModel() {
        private val _homeFlow = MutableStateFlow<GetHomeEvents>(GetHomeEvents.Idle)
        val homeFlow: StateFlow<GetHomeEvents> = _homeFlow

        private val _sectionList = MutableStateFlow<List<HomeSectionAdapterItem>?>(null)
    }
