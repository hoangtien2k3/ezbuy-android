package com.ezbuy.domain.model.detail

import com.ezbuy.common.model.DomainModel

data class OtherProducts(
    val productImage: String? = null,
    val productName: String? = null,
    val subText: String? = null
) : DomainModel