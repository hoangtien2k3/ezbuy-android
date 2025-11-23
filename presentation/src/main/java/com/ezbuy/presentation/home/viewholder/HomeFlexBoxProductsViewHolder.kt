package com.ezbuy.presentation.home.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.ezbuy.domain.model.home.ProductItem
import com.ezbuy.home.databinding.RowHomeFlexBoxProductsBinding
import com.ezbuy.presentation.home.adapter.HomeFlexBoxProductsAdapter
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent

class HomeFlexBoxProductsViewHolder(private val binding: RowHomeFlexBoxProductsBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(
        productItem: List<ProductItem>,
        sectionTitle: String,
    ) {
        val productLayoutManager =
            FlexboxLayoutManager(binding.root.context).apply {
                flexDirection = FlexDirection.ROW
                justifyContent = JustifyContent.FLEX_START
            }

        binding.homeFlexBoxSectionTitle.text = sectionTitle
        binding.homeFlexBoxRecyclerView.apply {
            layoutManager = productLayoutManager
            adapter = HomeFlexBoxProductsAdapter(ArrayList(productItem))
            (adapter as HomeFlexBoxProductsAdapter).updateList(productItem)
        }
    }
}
