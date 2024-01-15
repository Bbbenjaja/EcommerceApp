package com.toolstodo.ecommerceapp.domain.usecases

import com.toolstodo.ecommerceapp.data.repository.StoreRepositoryImpl
import com.toolstodo.ecommerceapp.domain.model.product.ProductResponse
import com.toolstodo.ecommerceapp.motherobject.ProductMotherObject
import io.kotlintest.shouldNotBe
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetProductsUsecaseTest {

    @MockK
    private val storeRepositoryImpl: StoreRepositoryImpl = mockk()

    private lateinit var getProductsUsecase: GetProductsUsecase

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        getProductsUsecase = GetProductsUsecase(storeRepositoryImpl)
    }

    @Test
    fun `GetProductUseCase should return products from Room when API connection fail`() {
        //Given
        coEvery {
            storeRepositoryImpl.getProductsFromNetwork(0, 0)
        } throws Exception("Api connection fail")

        coEvery { storeRepositoryImpl.getProductsFromCache() } returns ProductMotherObject.responseApi

        coEvery { storeRepositoryImpl.getAllFavoriteProducts() } returns ProductResponse(0, emptyList())

        runBlocking {
            //When
            val productResponseModel = getProductsUsecase(0, 0)

            //Then
            productResponseModel.products.size shouldNotBe 0
        }

    }

}