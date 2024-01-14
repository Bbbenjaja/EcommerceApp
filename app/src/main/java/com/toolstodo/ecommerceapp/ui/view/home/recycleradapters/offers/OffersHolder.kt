package com.toolstodo.ecommerceapp.ui.view.home.recycleradapters.offers

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.toolstodo.ecommerceapp.R
import com.toolstodo.ecommerceapp.databinding.OfferItemBinding
import com.toolstodo.ecommerceapp.domain.model.product.Product

class OffersHolder(itemView: View, private val context: Context) :
    RecyclerView.ViewHolder(itemView) {

    private val binding: OfferItemBinding = OfferItemBinding.bind(itemView)

    fun render(product: Product, onOfferClick: (Product) -> Unit) {
        with(binding) {
            txtProductName.text = product.title

            val priceText = context.getString(R.string.dollar_sign_label) + product.price
            txtPrice.text = priceText

            val discountText =
                context.getString(R.string.minus_sign_label) + product.discountPercentage + context.getString(
                    R.string.percent_sign_label
                )
            txtDiscount.text = discountText

            Picasso.get().load(product.thumbnail).into(imgProduct)
        }

        itemView.setOnClickListener {
            onOfferClick(product)
        }
    }

}