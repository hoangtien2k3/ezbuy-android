package com.ezbuy.presentation.home.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.ezbuy.domain.model.home.BannerItem
import com.ezbuy.home.databinding.RowHomeBannerItemBinding
import com.ezbuy.presentation.common.extension.setImageUrl

class HomeBannerItemViewHolder(private val binding: RowHomeBannerItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(
        bannerItem: BannerItem,
        clickListener: ((String?) -> Unit)?,
    ) {
        binding.bannerImageView.setImageUrl(bannerItem.image)
        bannerClick(clickListener, bannerItem)
    }

    private fun bannerClick(
        clickListener: ((String?) -> Unit)?,
        bannerItem: BannerItem,
    ) {
        binding.bannerCardView.setOnClickListener {
            clickListener?.invoke(
                bannerItem.navigationData,
            )
        }
    }
}
