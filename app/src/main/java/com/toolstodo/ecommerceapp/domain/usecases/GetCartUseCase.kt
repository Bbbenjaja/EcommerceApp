package com.toolstodo.ecommerceapp.domain.usecases

import com.toolstodo.ecommerceapp.data.local.entities.cart.toDomain
import com.toolstodo.ecommerceapp.data.repository.StoreRepositoryImpl
import com.toolstodo.ecommerceapp.domain.model.cart.Cart
import javax.inject.Inject

class GetCartUseCase @Inject constructor(
    private val storeRepositoryImpl: StoreRepositoryImpl
) {

    suspend operator fun invoke(): Cart{
        return Cart(productList = storeRepositoryImpl.getAllProductsCart().map { it.toDomain() })
    }

}