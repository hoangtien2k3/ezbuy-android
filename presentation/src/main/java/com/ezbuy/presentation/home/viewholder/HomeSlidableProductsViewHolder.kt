package com.ezbuy.presentation.home.viewholder

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ezbuy.domain.model.home.ProductItem
import com.ezbuy.home.databinding.RowHomeSlidableProductsBinding
import com.ezbuy.presentation.home.adapter.HomeSlidableProductAdapter

class HomeSlidableProductsViewHolder(private val binding: RowHomeSlidableProductsBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(
        productItem: List<ProductItem>,
        sectionTitle: String,
    ) {
        val productLayoutManager =
            LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL, false)

        binding.homeSlidableSectionTitle.text = sectionTitle
        binding.homeSlidableRecyclerView.apply {
            layoutManager = productLayoutManager
            adapter = HomeSlidableProductAdapter(ArrayList(productItem))
            (adapter as HomeSlidableProductAdapter).updateList(productItem)
        }
    }
}
