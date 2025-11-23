package com.ezbuy.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ezbuy.common.extensions.onFailure
import com.ezbuy.common.extensions.onSuccess
import com.ezbuy.domain.model.detail.DetailModel
import com.ezbuy.domain.usecase.GetDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailSharedViewModel @Inject constructor(
    private val getDetailUseCase: GetDetailUseCase
) : ViewModel() {

    private val _detailFlow = MutableSharedFlow<DetailModel?>(extraBufferCapacity = 1)
    val detailFlow = _detailFlow.asSharedFlow()

    fun getDetail() {
        viewModelScope.launch {
            getDetailUseCase().onSuccess { detailData ->
                _detailFlow.emit(detailData)
            }.onFailure {
            }
        }
    }
}