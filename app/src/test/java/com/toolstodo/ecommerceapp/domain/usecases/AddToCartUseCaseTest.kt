package com.toolstodo.ecommerceapp.domain.usecases

import com.toolstodo.ecommerceapp.data.local.entities.cart.toDomain
import com.toolstodo.ecommerceapp.data.model.products.toDomain
import com.toolstodo.ecommerceapp.data.model.products.toProductCartEntity
import com.toolstodo.ecommerceapp.data.model.products.toRoomDB
import com.toolstodo.ecommerceapp.data.repository.StoreRepositoryImpl
import com.toolstodo.ecommerceapp.domain.model.cart.toRoomDB
import com.toolstodo.ecommerceapp.domain.model.product.toProductCart
import com.toolstodo.ecommerceapp.motherobject.ProductMotherObject
import io.kotlintest.shouldBe
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class AddToCartUseCaseTest {
    @MockK
    private val storeRepositoryImpl: StoreRepositoryImpl = mockk()
    private lateinit var addToCartUseCase: AddToCartUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        addToCartUseCase = AddToCartUseCase(storeRepositoryImpl)
    }

    @Test
    fun `AddToCartUseCase shouldn't add to cart when there is not stock`() {
        //Given
        val productToAdd =
            ProductMotherObject.responseApi.products.first().toDomain(emptyList()).toProductCart()
        val productsCart = ProductMotherObject.cart.productList.map { it.toRoomDB() }

        coEvery { storeRepositoryImpl.getProductStockById(productToAdd.id) } returns 0
        coEvery { storeRepositoryImpl.getAllProductsCart() } returns productsCart

        runBlocking {
            //When
            val isInCart = addToCartUseCase(productToAdd)

            //Then
            assert(!isInCart)
        }

    }

    @Test
    fun `AddToCartUseCase should update the product if the product is already in the cart`() {
        //Given
        val productsCart = ProductMotherObject.cart.productList.map { it.toRoomDB() }
        val productToAdd = productsCart.first()

        coEvery { storeRepositoryImpl.getProductStockById(productToAdd.id) } returns 100
        coEvery { storeRepositoryImpl.getAllProductsCart() } returns productsCart
        coEvery { storeRepositoryImpl.updateProductCart(any()) } returns Unit
        coEvery { storeRepositoryImpl.addProductToCart(any()) } returns Unit

        runBlocking {
            //When
            val isUpdated = addToCartUseCase(productToAdd.toDomain())

            //Then
            isUpdated shouldBe true
        }

    }

}