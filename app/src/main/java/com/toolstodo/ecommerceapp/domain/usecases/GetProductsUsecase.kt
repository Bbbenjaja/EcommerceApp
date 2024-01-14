package com.toolstodo.ecommerceapp.domain.usecases

import com.toolstodo.ecommerceapp.data.model.products.toDomain
import com.toolstodo.ecommerceapp.data.model.products.toRoomDB
import com.toolstodo.ecommerceapp.data.repository.StoreRepositoryImpl
import com.toolstodo.ecommerceapp.domain.model.product.ProductResponse
import com.toolstodo.ecommerceapp.domain.model.product.toRoomDB
import javax.inject.Inject

class GetProductsUsecase @Inject constructor(
    private val storeRepositoryImpl: StoreRepositoryImpl,
) {

    suspend operator fun invoke(skip: Int, limit: Int): ProductResponse {
        val favList = storeRepositoryImpl.getAllFavoriteProducts().products.map { it.toRoomDB() }

        return try {
            val productsNetwork = storeRepositoryImpl.getProductsFromNetwork(skip, limit)

            storeRepositoryImpl.clearProducts()
            storeRepositoryImpl.insertProductsCache(productsNetwork.products.map {
                it.toRoomDB(
                    favList
                )
            })

            productsNetwork.toDomain(favList)
        } catch (e: Exception) {
            storeRepositoryImpl.getProductsFromCache().toDomain(favList)
        }

    }

}