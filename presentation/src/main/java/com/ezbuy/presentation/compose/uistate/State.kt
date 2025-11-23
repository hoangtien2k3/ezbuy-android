package com.ezbuy.presentation.compose.uistate

import com.ezbuy.domain.model.list.ListProductsModel

data class State(
    var lazyColumnList: List<ListProductsModel>? = emptyList(),
    var isLoading: Boolean = false,
)
