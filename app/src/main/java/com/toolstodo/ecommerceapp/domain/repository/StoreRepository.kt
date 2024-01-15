package com.toolstodo.ecommerceapp.domain.repository

import com.toolstodo.ecommerceapp.data.local.entities.cart.ProductCartEntity
import com.toolstodo.ecommerceapp.data.local.entities.product.ProductEntity
import com.toolstodo.ecommerceapp.data.model.products.ProductResponseModel
import com.toolstodo.ecommerceapp.domain.model.product.ProductResponse

interface StoreRepository {

    suspend fun getProductsFromNetwork(skip: Int = 0, limit: Int = 0): ProductResponseModel

    suspend fun getProductsByCategoryFromNetwork(category: String): ProductResponseModel

    suspend fun getProductsByNameFromNetwork(
        skip: Int,
        limit: Int,
        name: String,
    ): ProductResponseModel

    suspend fun getProductStockById(id: Int): Int

    suspend fun getProductsFromCache(): ProductResponseModel

    suspend fun getProductsByCategoryFromCache(category: String): ProductResponseModel

    suspend fun getProductsByNameFromCache(name: String): ProductResponseModel

    suspend fun insertProductsCache(products: List<ProductEntity>)

    suspend fun changeFavoriteStatus(isFav: Boolean, id: Int)

    suspend fun getAllFavoriteProducts(): ProductResponse

    suspend fun clearProducts()

    //Cart functions
    suspend fun addProductToCart(product:ProductCartEntity)

    suspend fun getAllProductsCart(): List<ProductCartEntity>

    suspend fun getProductCartById(id: Int): ProductCartEntity

    suspend fun updateProductCart(product: ProductCartEntity)

    suspend fun clearAllProductsCart()

    suspend fun removeProductCart(product: ProductCartEntity)

}