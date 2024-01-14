package com.toolstodo.ecommerceapp.domain.usecases

import com.toolstodo.ecommerceapp.data.repository.StoreRepositoryImpl
import com.toolstodo.ecommerceapp.domain.model.product.ProductResponse
import javax.inject.Inject

class GetAllFavoriteProductsUsecase @Inject constructor(
    private val storeRepositoryImpl: StoreRepositoryImpl
) {

    suspend operator fun invoke(): ProductResponse{
        return storeRepositoryImpl.getAllFavoriteProducts()
    }

}