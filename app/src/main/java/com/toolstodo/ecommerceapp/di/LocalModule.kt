package com.toolstodo.ecommerceapp.di

import android.content.Context
import androidx.room.Room
import com.toolstodo.ecommerceapp.data.local.StoreDatabase
import com.toolstodo.ecommerceapp.data.local.dao.ProductCartDao
import com.toolstodo.ecommerceapp.data.local.dao.ProductDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    private const val STORE_DATABASE_NAME = "store_database"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context): StoreDatabase {
        return Room.databaseBuilder(context, StoreDatabase::class.java, STORE_DATABASE_NAME).build()
    }

    @Singleton
    @Provides
    fun provideProductDao(db: StoreDatabase): ProductDao = db.getProductDao()

    @Singleton
    @Provides
    fun provideProductCartDao(db: StoreDatabase): ProductCartDao = db.getProductCartDao()

}