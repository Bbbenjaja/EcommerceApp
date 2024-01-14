package com.toolstodo.ecommerceapp.ui.view.home.recycleradapters.offers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.toolstodo.ecommerceapp.R
import com.toolstodo.ecommerceapp.domain.model.product.Product
import com.toolstodo.ecommerceapp.ui.view.home.recycleradapters.ProductDataDiffUtil

class OffersAdapter(
    private var productList: List<Product> = emptyList(),
    private val onOfferClick: (Product) -> Unit,
) :
    RecyclerView.Adapter<OffersHolder>() {

    fun updateList(newList: List<Product>) {
        val productDataDiff = ProductDataDiffUtil(productList, newList)
        val result = DiffUtil.calculateDiff(productDataDiff)

        productList = newList
        result.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OffersHolder {
        return OffersHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.offer_item, parent, false),
            parent.context
        )
    }

    override fun getItemCount(): Int = productList.size

    override fun onBindViewHolder(holder: OffersHolder, position: Int) {
        holder.render(productList[position], onOfferClick)
    }
}