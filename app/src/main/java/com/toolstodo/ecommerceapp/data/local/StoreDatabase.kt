package com.toolstodo.ecommerceapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.toolstodo.ecommerceapp.data.local.converters.ProductConverters
import com.toolstodo.ecommerceapp.data.local.dao.ProductCartDao
import com.toolstodo.ecommerceapp.data.local.dao.ProductDao
import com.toolstodo.ecommerceapp.data.local.entities.cart.ProductCartEntity
import com.toolstodo.ecommerceapp.data.local.entities.product.ProductEntity

@Database(entities = [ProductEntity::class, ProductCartEntity::class], version = 1, exportSchema = false)
@TypeConverters(ProductConverters::class)
abstract class StoreDatabase: RoomDatabase() {

    abstract fun getProductDao(): ProductDao

    abstract fun getProductCartDao(): ProductCartDao

}