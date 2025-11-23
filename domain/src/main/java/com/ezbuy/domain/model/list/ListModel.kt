package com.ezbuy.domain.model.list

import com.ezbuy.common.model.DomainModel

data class ListModel(
    val productList: List<ListProductsModel>?,
    val productLimit: Int?,
    val totalCount: Int?,
) : DomainModel

data class ListProductsModel(
    val productId: String,
    val productImage: String,
    val text: String,
    val subText: String,
    val review: String,
    val questions: String,
    val rating: String,
) : DomainModel
