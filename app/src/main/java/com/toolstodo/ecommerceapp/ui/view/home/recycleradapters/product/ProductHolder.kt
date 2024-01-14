package com.toolstodo.ecommerceapp.ui.view.home.recycleradapters.product

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.toolstodo.ecommerceapp.R
import com.toolstodo.ecommerceapp.databinding.ProductItemBinding
import com.toolstodo.ecommerceapp.domain.model.product.Product

class ProductHolder(itemView: View, private val context:Context) : RecyclerView.ViewHolder(itemView) {

    private val binding: ProductItemBinding = ProductItemBinding.bind(itemView)

    fun render(
        product: Product,
        onProductClick: (Product) -> Unit,
    ) {
        with(binding) {

            imgProduct.post {
                Picasso.get().load(product.thumbnail).resize(imgProduct.width, imgProduct.height)
                    .into(imgProduct)
            }

            txtCategorie.text = product.category
            txtProductName.text = product.title
            val priceText = context.getString(R.string.dollar_sign_label) + product.price
            txtPrice.text = priceText
            txtRate.text = "${product.rating}"

        }

        itemView.setOnClickListener {
            onProductClick(product)
        }

    }

}