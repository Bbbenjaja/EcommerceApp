package com.toolstodo.ecommerceapp.domain.model.product

import com.toolstodo.ecommerceapp.data.local.entities.product.ProductEntity
import java.io.Serializable

data class Product(
    val brand: String,
    val category: String,
    val description: String,
    val discountPercentage: Double,
    val id: Int,
    val images: List<String>,
    val price: Int,
    val rating: Double,
    val stock: Int,
    val thumbnail: String,
    val title: String,
    var isFavorite: Boolean = false,
) : Serializable

fun Product.toRoomDB() = ProductEntity(
    brand = brand,
    category = category,
    description = description,
    discountPercentage = discountPercentage,
    id = id,
    images = images,
    price = price,
    rating = rating,
    stock = stock,
    thumbnail = thumbnail,
    title = title,
    isFavorite = isFavorite
)