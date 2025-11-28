package com.ezbuy.app.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ezbuy.app.home.viewholder.HomeFlexBoxProductItemViewHolder
import com.ezbuy.domain.model.home.ProductItem
import com.ezbuy.app.databinding.HomeFlexBoxProductItemBinding

class HomeFlexBoxProductsAdapter(
    private val list: ArrayList<ProductItem>,
) : RecyclerView.Adapter<HomeFlexBoxProductItemViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): HomeFlexBoxProductItemViewHolder {
        return HomeFlexBoxProductItemViewHolder(
            HomeFlexBoxProductItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            ),
        )
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(
        holder: HomeFlexBoxProductItemViewHolder,
        position: Int,
    ) {
        val data = list[position]
        holder.bind(data)
    }

    fun updateList(newList: List<ProductItem>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }
}
