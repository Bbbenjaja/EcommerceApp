package com.toolstodo.ecommerceapp.ui.view.home.recycleradapters.searcher

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.toolstodo.ecommerceapp.databinding.ProductSearchItemBinding
import com.toolstodo.ecommerceapp.domain.model.product.Product

class ProductSearchHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val binding = ProductSearchItemBinding.bind(itemView)

    fun render(product: Product, onProductClick: (Product) -> Unit) {
        with(binding) {

            imgProduct.post {
                Picasso.get().load(product.thumbnail).resize(imgProduct.width, imgProduct.height)
                    .into(imgProduct)
            }
            txtBrand.text = product.brand
            txtProductName.text = product.title

            val priceText = "$${product.price}"
            txtPrice.text = priceText
        }

        itemView.setOnClickListener {
            onProductClick(product)
        }
    }

}