package com.toolstodo.ecommerceapp.domain.model.cart


data class Cart(
    val productList: List<ProductCart>,
    val discountedTotal: Double = productList.sumOf { it.discountedPrice },
    val total: Double = productList.sumOf { it.total },
    val totalProducts: Int = productList.size,
    val totalQuantity: Int = productList.sumOf { it.quantity },
)
