package com.ezbuy.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ezbuy.domain.model.home.BannerItem
import com.ezbuy.home.databinding.RowHomeBannerItemBinding
import com.ezbuy.presentation.home.viewholder.HomeBannerItemViewHolder

class HomeBannerAdapter(
    private val bannerItems: List<BannerItem>,
    private val clickListener: ((String?) -> Unit)?,
) : RecyclerView.Adapter<HomeBannerItemViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): HomeBannerItemViewHolder {
        return HomeBannerItemViewHolder(
            RowHomeBannerItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            ),
        )
    }

    override fun onBindViewHolder(
        holder: HomeBannerItemViewHolder,
        position: Int,
    ) {
        val currentItem = bannerItems[position % bannerItems.size]
        holder.bind(currentItem, clickListener)
    }

    override fun getItemCount(): Int = bannerItems.size * 1000
}
