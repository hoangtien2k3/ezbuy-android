package com.ezbuy.presentation.home.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.ezbuy.domain.model.home.ProductItem
import com.ezbuy.home.databinding.HomeSlidingProductItemBinding
import com.ezbuy.presentation.common.extension.setImageUrl

class HomeSlidableProductItemViewHolder(private val binding: HomeSlidingProductItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(product: ProductItem) {
        with(binding) {
            slidingProductNameTextView.text = product.text
            slidingProductPriceTextView.text = product.subText
            slidingProductImageView.setImageUrl(product.productImage)
        }
    }
}
