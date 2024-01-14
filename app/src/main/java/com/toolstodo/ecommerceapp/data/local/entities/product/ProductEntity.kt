package com.toolstodo.ecommerceapp.data.local.entities.product

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.toolstodo.ecommerceapp.data.model.products.ProductModel
import com.toolstodo.ecommerceapp.domain.model.product.Product

@Entity(tableName = "product_table")
data class ProductEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "brand") val brand: String,
    @ColumnInfo(name = "category") val category: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "discountPercentage") val discountPercentage: Double,
    @ColumnInfo(name = "images") val images: List<String>,
    @ColumnInfo(name = "price") val price: Int,
    @ColumnInfo(name = "rating") val rating: Double,
    @ColumnInfo(name = "stock") val stock: Int,
    @ColumnInfo(name = "thumbnail") val thumbnail: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "isFavorite") var isFavorite: Boolean = false,
)

fun ProductEntity.toDomain() = Product(
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

fun ProductEntity.toModel() = ProductModel(
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
)