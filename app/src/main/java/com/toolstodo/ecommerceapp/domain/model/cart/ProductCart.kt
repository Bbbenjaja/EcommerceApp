package com.toolstodo.ecommerceapp.domain.model.cart

import com.toolstodo.ecommerceapp.data.local.entities.cart.ProductCartEntity

data class ProductCart(
    val id: Int,
    val price: Double,
    val discountPercentage: Double,
    var quantity: Int,
    val total: Double = quantity * price,
    val discountedPrice: Double = (total - (total * discountPercentage * 0.01f)),
    val thumbnail: String,
    val title: String,
)

fun ProductCart.toRoomDB() = ProductCartEntity(
    id = id,
    price = price,
    discountPercentage = discountPercentage,
    quantity = quantity,
    total = total,
    discountedPrice = discountedPrice,
    thumbnail = thumbnail,
    title = title
)