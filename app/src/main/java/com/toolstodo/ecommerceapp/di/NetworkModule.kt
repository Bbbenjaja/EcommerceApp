package com.toolstodo.ecommerceapp.di

import com.google.gson.Gson
import com.toolstodo.ecommerceapp.data.network.StoreApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideStoreApiClient(retrofit: Retrofit): StoreApiClient {
        return retrofit.create(StoreApiClient::class.java)
    }

    @Singleton
    @Provides
    fun provideGson(): Gson = Gson()

}