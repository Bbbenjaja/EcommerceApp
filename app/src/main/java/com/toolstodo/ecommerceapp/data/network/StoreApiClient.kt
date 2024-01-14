package com.toolstodo.ecommerceapp.data.network

import com.toolstodo.ecommerceapp.data.model.products.ProductModel
import com.toolstodo.ecommerceapp.data.model.products.ProductResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface StoreApiClient {

    @GET("products")
    suspend fun getProducts(
        @Query("skip") skip: Int,
        @Query("limit") limit: Int,
    ): Response<ProductResponseModel>

    @GET("products/category/{category}")
    suspend fun getProductsByCategory(@Path("category") category: String): Response<ProductResponseModel>

    @GET("products/search")
    suspend fun getProductsByName(
        @Query("skip") skip: Int,
        @Query("limit") limit: Int,
        @Query("q") name: String,
    ): Response<ProductResponseModel>

    @GET("products/{id}")
    suspend fun getProductById(@Path("id")id: Int): Response<ProductModel>

}