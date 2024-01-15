package com.toolstodo.ecommerceapp.domain.usecases

import android.util.Log
import com.toolstodo.ecommerceapp.data.local.entities.cart.toDomain
import com.toolstodo.ecommerceapp.data.repository.StoreRepositoryImpl
import com.toolstodo.ecommerceapp.domain.model.cart.Cart
import com.toolstodo.ecommerceapp.domain.model.cart.ProductCart
import com.toolstodo.ecommerceapp.domain.model.cart.toRoomDB
import javax.inject.Inject

class AddToCartUseCase @Inject constructor(
    private val storeRepositoryImpl: StoreRepositoryImpl,
) {

    suspend operator fun invoke(productCart: ProductCart): Boolean {
        try {
            val cart = Cart(productList = storeRepositoryImpl.getAllProductsCart().map { it.toDomain() })
            val productFromCart = cart.productList.firstOrNull { it.id == productCart.id }
            val productStock = storeRepositoryImpl.getProductStockById(productCart.id)

            if (productStock == 0) {
                return false
            }

            if (productFromCart == null) {
                if (productCart.quantity > productStock) {
                    productCart.quantity = productStock
                }
                storeRepositoryImpl.addProductToCart(productCart.toRoomDB())
            } else {

                if (productFromCart.quantity + productCart.quantity > productStock) {
                    productCart.quantity = productStock
                }else{
                    productCart.quantity = productFromCart.quantity + productCart.quantity
                }

                storeRepositoryImpl.updateProductCart(productCart.toRoomDB())
            }

            return true
        } catch (e: Exception) {
            //Log.d("Error adding to cart", e.message.toString())
            return false
        }

    }

}