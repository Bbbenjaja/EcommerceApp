package com.toolstodo.ecommerceapp.data.repository

import com.toolstodo.ecommerceapp.data.local.dao.ProductCartDao
import com.toolstodo.ecommerceapp.data.local.dao.ProductDao
import com.toolstodo.ecommerceapp.data.local.entities.cart.ProductCartEntity
import com.toolstodo.ecommerceapp.data.local.entities.product.ProductEntity
import com.toolstodo.ecommerceapp.data.local.entities.product.toDomain
import com.toolstodo.ecommerceapp.data.local.entities.product.toModel
import com.toolstodo.ecommerceapp.data.model.products.ProductResponseModel
import com.toolstodo.ecommerceapp.data.network.StoreApiClient
import com.toolstodo.ecommerceapp.domain.model.product.ProductResponse
import com.toolstodo.ecommerceapp.domain.repository.StoreRepository
import javax.inject.Inject

class StoreRepositoryImpl @Inject constructor(
    private val storeApiClient: StoreApiClient,
    private val productDao: ProductDao,
    private val productCartDao: ProductCartDao
) : StoreRepository {
    override suspend fun getProductsFromNetwork(skip: Int, limit: Int): ProductResponseModel {
        return storeApiClient.getProducts(skip, limit).body()!!
    }

    override suspend fun getProductsByCategoryFromNetwork(category: String): ProductResponseModel {
        return storeApiClient.getProductsByCategory(category).body()!!
    }

    override suspend fun getProductsByNameFromNetwork(
        skip: Int,
        limit: Int,
        name: String,
    ): ProductResponseModel {
        return storeApiClient.getProductsByName(skip, limit, name).body()!!
    }

    override suspend fun getProductStockById(id: Int): Int {
        return try {
            val productNetwork = storeApiClient.getProductById(id).body()
            productNetwork?.stock ?: 0
        }catch (e: Exception) {
            val productCache = productDao.getProductById(id)
            productCache.stock
        }
    }

    override suspend fun getProductsFromCache(): ProductResponseModel {
        return ProductResponseModel(products = productDao.getAllProducts().map { it.toModel() })
    }

    override suspend fun getProductsByCategoryFromCache(category: String): ProductResponseModel {
        return ProductResponseModel(
            products = productDao.getProductsByCategory(category).map { it.toModel() })
    }

    override suspend fun getProductsByNameFromCache(name: String): ProductResponseModel {
        return ProductResponseModel(
            products = productDao.getProductsByName(name).map { it.toModel() })
    }

    override suspend fun insertProductsCache(products: List<ProductEntity>) {
        productDao.insertAll(products)
    }

    override suspend fun changeFavoriteStatus(isFav: Boolean, id: Int) {
        productDao.changeFavoriteStatus(isFav, id)
    }

    override suspend fun getAllFavoriteProducts(): ProductResponse {
        return ProductResponse(
            products = productDao.getAllFavoriteProducts().map { it.toDomain() })
    }

    override suspend fun clearProducts() {
        productDao.deleteAllProducts()
    }

    //Cart functions
    override suspend fun addProductToCart(product:ProductCartEntity){
        productCartDao.addProductToCart(product)
    }

    override suspend fun getAllProductsCart(): List<ProductCartEntity> {
        return productCartDao.getAllProductCarts()
    }

    override suspend fun getProductCartById(id: Int): ProductCartEntity {
        return productCartDao.getProductCartById(id)
    }

    override suspend fun updateProductCart(product: ProductCartEntity) {
        productCartDao.updateProductCart(product)
    }

    override suspend fun clearAllProductsCart() {
        productCartDao.clearAllProductsCart()
    }

    override suspend fun removeProductCart(product: ProductCartEntity) {
        productCartDao.removeProductCart(product)
    }

}