package com.toolstodo.ecommerceapp.motherobject

import com.toolstodo.ecommerceapp.data.model.products.ProductModel
import com.toolstodo.ecommerceapp.data.model.products.ProductResponseModel
import com.toolstodo.ecommerceapp.data.model.products.toDomain
import com.toolstodo.ecommerceapp.data.model.products.toProductCartEntity
import com.toolstodo.ecommerceapp.domain.model.cart.Cart
import com.toolstodo.ecommerceapp.domain.model.product.toProductCart
import com.toolstodo.ecommerceapp.domain.model.product.toRoomDB

object ProductMotherObject {

    val productModelList = listOf(
        ProductModel(
            id = 1,
            title = "iPhone 9",
            description = "An apple mobile which is nothing like apple",
            price = 549,
            discountPercentage = 12.96,
            rating = 4.69,
            stock = 94,
            brand = "Apple",
            category = "smartphones",
            thumbnail = "https://cdn.dummyjson.com/product-images/1/thumbnail.jpg",
            images = listOf(
                "https://cdn.dummyjson.com/product-images/1/1.jpg",
                "https://cdn.dummyjson.com/product-images/1/2.jpg",
                "https://cdn.dummyjson.com/product-images/1/3.jpg",
                "https://cdn.dummyjson.com/product-images/1/4.jpg",
                "https://cdn.dummyjson.com/product-images/1/thumbnail.jpg"
            )
        ),
        ProductModel(
            id = 2,
            title = "iPhone X",
            description = "SIM-Free, Model A19211 6.5-inch Super Retina HD display with OLED technology A12 Bionic chip with ...",
            price = 899,
            discountPercentage = 17.94,
            rating = 4.44,
            stock = 34,
            brand = "Apple",
            category = "smartphones",
            thumbnail = "https://cdn.dummyjson.com/product-images/2/thumbnail.jpg",
            images = listOf(
                "https://cdn.dummyjson.com/product-images/2/1.jpg",
                "https://cdn.dummyjson.com/product-images/2/2.jpg",
                "https://cdn.dummyjson.com/product-images/2/3.jpg",
                "https://cdn.dummyjson.com/product-images/2/thumbnail.jpg"
            )
        )
    )

    val responseApi = ProductResponseModel(products = productModelList)

    val cart = Cart(productList = productModelList.map { it.toDomain(emptyList()) }.map { it.toProductCart() })

}