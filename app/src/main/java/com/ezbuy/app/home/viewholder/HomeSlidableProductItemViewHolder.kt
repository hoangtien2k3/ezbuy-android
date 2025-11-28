package com.ezbuy.app.home.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.ezbuy.app.common.extension.setImageUrl
import com.ezbuy.domain.model.home.ProductItem
import com.ezbuy.app.databinding.HomeSlidingProductItemBinding

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
