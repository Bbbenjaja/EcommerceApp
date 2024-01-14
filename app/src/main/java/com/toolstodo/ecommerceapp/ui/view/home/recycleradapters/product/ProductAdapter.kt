package com.toolstodo.ecommerceapp.ui.view.home.recycleradapters.product

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.toolstodo.ecommerceapp.R
import com.toolstodo.ecommerceapp.domain.model.product.Product
import com.toolstodo.ecommerceapp.ui.view.home.recycleradapters.ProductDataDiffUtil

class ProductAdapter(
    private var productList: List<Product> = emptyList(),
    private val onProductClick: (Product) -> Unit,
) :
    RecyclerView.Adapter<ProductHolder>() {

    fun updateList(newList: List<Product>) {
        val productDataDiff = ProductDataDiffUtil(productList, newList)
        val result = DiffUtil.calculateDiff(productDataDiff)

        productList = newList
        result.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        return ProductHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.product_item, parent, false),
            parent.context
        )
    }

    override fun getItemCount(): Int = productList.size

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        holder.render(productList[position], onProductClick)
    }
}