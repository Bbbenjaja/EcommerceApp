package com.toolstodo.ecommerceapp.ui.view.home.recycleradapters.searcher

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.toolstodo.ecommerceapp.R
import com.toolstodo.ecommerceapp.domain.model.product.Product
import com.toolstodo.ecommerceapp.ui.view.home.recycleradapters.ProductDataDiffUtil

class ProductSearchAdapter(
    private var productList: List<Product> = emptyList(),
    private val onProductClick: (Product) -> Unit,
) :
    RecyclerView.Adapter<ProductSearchHolder>() {

    fun updateList(newList: List<Product>) {
        val productDataDiff = ProductDataDiffUtil(productList, newList)
        val result = DiffUtil.calculateDiff(productDataDiff)

        productList = newList
        result.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductSearchHolder {
        return ProductSearchHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.product_search_item, parent, false)
        )
    }

    override fun getItemCount(): Int = productList.size

    override fun onBindViewHolder(holder: ProductSearchHolder, position: Int) {
        holder.render(productList[position], onProductClick)
    }

}