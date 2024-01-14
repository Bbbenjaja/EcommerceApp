package com.toolstodo.ecommerceapp.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.toolstodo.ecommerceapp.data.local.entities.cart.ProductCartEntity

@Dao
interface ProductCartDao {

    @Query("SELECT * FROM product_cart_table")
    suspend fun getAllProductCarts(): List<ProductCartEntity>

    @Query("SELECT * FROM product_cart_table WHERE id = :id")
    suspend fun getProductCartById(id: Int): ProductCartEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addProductToCart(product: ProductCartEntity)

    @Update
    suspend fun updateProductCart(product: ProductCartEntity)

    @Query("DELETE FROM product_cart_table")
    suspend fun clearAllProductsCart()

    @Delete
    suspend fun removeProductCart(product: ProductCartEntity)


}