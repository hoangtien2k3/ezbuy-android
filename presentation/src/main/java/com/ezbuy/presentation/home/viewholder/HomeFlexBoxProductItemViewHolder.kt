package com.ezbuy.presentation.home.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.ezbuy.domain.model.home.ProductItem
import com.ezbuy.presentation.common.extension.hide
import com.ezbuy.presentation.common.extension.setImageUrl
import com.ezbuy.presentation.common.extension.show
import com.ezbuy.presentation.databinding.HomeFlexBoxProductItemBinding

class HomeFlexBoxProductItemViewHolder(private val binding: HomeFlexBoxProductItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(product: ProductItem) {
        with(binding) {
            flexBoxProductNameTextView.text = product.text
            flexBoxProductPriceTextView.text = product.subText
            flexBoxProductPieceTextView.text = product.piece
            flexBoxProductImageView.setImageUrl(product.productImage)

            if (product.soldOutText != null) {
                flexBoxProductOverlayView.show()
                flexBoxProductSoldOutTextView.show()
                flexBoxProductSoldOutTextView.text = product.soldOutText
            } else {
                flexBoxProductOverlayView.hide()
                flexBoxProductSoldOutTextView.hide()
            }
        }
    }
}
