package com.toolstodo.ecommerceapp.data.local.entities.cart

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.toolstodo.ecommerceapp.domain.model.cart.ProductCart

@Entity(tableName = "product_cart_table")
data class ProductCartEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "price") val price: Double,
    @ColumnInfo(name = "discountPercentage") val discountPercentage: Double,
    @ColumnInfo(name = "quantity") val quantity: Int = 1,
    @ColumnInfo(name = "total") val total: Double = quantity * price,
    @ColumnInfo(name = "discountedPrice") val discountedPrice: Double = (total - (total * discountPercentage * 0.01f)),
    @ColumnInfo(name = "thumbnail") val thumbnail: String,
    @ColumnInfo(name = "title") val title: String,
)

fun ProductCartEntity.toDomain() = ProductCart(
    id = id,
    price = price,
    discountPercentage = discountPercentage,
    quantity = quantity,
    total = total,
    discountedPrice = discountedPrice,
    thumbnail = thumbnail,
    title = title
)