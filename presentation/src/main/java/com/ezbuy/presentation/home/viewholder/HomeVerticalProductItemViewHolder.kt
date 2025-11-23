package com.ezbuy.presentation.home.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.ezbuy.domain.model.home.ProductItem
import com.ezbuy.home.databinding.HomeVerticalProductItemBinding
import com.ezbuy.presentation.common.extension.setImageUrl

class HomeVerticalProductItemViewHolder(private val binding: HomeVerticalProductItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(product: ProductItem) {
        with(binding) {
            verticalProductNameTextView.text = product.text
            verticalProductPriceTextView.text = product.subText
            verticalProductImageView.setImageUrl(product.productImage)
        }
    }
}