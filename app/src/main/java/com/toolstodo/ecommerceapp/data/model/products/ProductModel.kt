package com.toolstodo.ecommerceapp.data.model.products

import com.toolstodo.ecommerceapp.data.local.entities.product.ProductEntity
import com.toolstodo.ecommerceapp.domain.model.product.Product

data class ProductModel(
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
)

fun ProductModel.toDomain(favList: List<ProductEntity>) = Product(
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
    isFavorite = favList.firstOrNull{ productEntity -> productEntity.id == id } != null
)

fun ProductModel.toRoomDB(favList: List<ProductEntity>) = ProductEntity(
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
    isFavorite = favList.firstOrNull{ productEntity -> productEntity.id == id } != null
)