package com.toolstodo.ecommerceapp.domain.usecases

import com.toolstodo.ecommerceapp.data.model.products.toDomain
import com.toolstodo.ecommerceapp.data.repository.StoreRepositoryImpl
import com.toolstodo.ecommerceapp.domain.model.product.ProductResponse
import com.toolstodo.ecommerceapp.domain.model.product.toRoomDB
import javax.inject.Inject

class GetProductsByNameUsecase @Inject constructor(
    private val storeRepositoryImpl: StoreRepositoryImpl,
) {

    suspend operator fun invoke(skip: Int, limit: Int, name: String): ProductResponse {
        val favList = storeRepositoryImpl.getAllFavoriteProducts().products.map { it.toRoomDB() }

        return try {
            val productsNetwork =
                storeRepositoryImpl.getProductsByNameFromNetwork(skip, limit, name)
                    .toDomain(favList)
            productsNetwork
        } catch (e: Exception) {
            storeRepositoryImpl.getProductsByNameFromCache(name).toDomain(favList)
        }
    }

}