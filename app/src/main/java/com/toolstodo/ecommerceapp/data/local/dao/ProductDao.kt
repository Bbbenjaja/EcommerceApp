package com.toolstodo.ecommerceapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.toolstodo.ecommerceapp.data.local.entities.product.ProductEntity

@Dao
interface ProductDao {

    @Query("SELECT * FROM product_table")
    suspend fun getAllProducts(): List<ProductEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(products: List<ProductEntity>)

    @Query("SELECT * FROM product_table WHERE id = :id")
    suspend fun getProductById(id: Int): ProductEntity

    @Query("SELECT * FROM product_table WHERE title LIKE '%' || :name || '%'")
    suspend fun getProductsByName(name: String): List<ProductEntity>

    @Query("SELECT * FROM product_table WHERE category = :category")
    suspend fun getProductsByCategory(category: String): List<ProductEntity>

    @Query("SELECT CASE WHEN EXISTS (SELECT 1 FROM product_table WHERE id = :id AND isFavorite = 1) THEN 1 ELSE 0 END")
    suspend fun isProductFavorite(id: Int): Int

    @Query("UPDATE product_table SET isFavorite = :isFav WHERE id = :id")
    suspend fun changeFavoriteStatus(isFav: Boolean, id: Int)

    @Query("SELECT * FROM product_table WHERE isFavorite = 1")
    suspend fun getAllFavoriteProducts(): List<ProductEntity>

    @Query("DELETE FROM product_table")
    suspend fun deleteAllProducts()

}