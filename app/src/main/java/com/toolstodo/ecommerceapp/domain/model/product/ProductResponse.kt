package com.toolstodo.ecommerceapp.domain.model.product

data class ProductResponse(
    var limit: Int = 0,
    var products: List<Product>,
    var skip: Int = 0,
    var total: Int = products.size,
)
