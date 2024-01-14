package com.toolstodo.ecommerceapp.data.model.products

import com.toolstodo.ecommerceapp.data.local.entities.product.ProductEntity
import com.toolstodo.ecommerceapp.domain.model.product.ProductResponse

data class ProductResponseModel(
    val limit: Int =0,
    val products: List<ProductModel>,
    val skip: Int = 0,
    val total: Int = products.size,
)

fun ProductResponseModel.toDomain(favList: List<ProductEntity>) =
    ProductResponse(
        limit = limit,
        products = products.map { it.toDomain(favList) },
        skip = skip,
        total = total
    )