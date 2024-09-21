package com.example.spizarka_v3.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Insert
    suspend fun addProduct(product: Product)

    @Insert
    suspend fun insertAll(products: kotlin.collections.List<com.example.spizarka_v3.data.Product>)

    @Delete
    suspend fun delete(products: List<Product>)

    @Update
    suspend fun update(product: Product)

    @Query("SELECT * FROM products_table")
    fun getAll(): Flow<List<Product>>

    @Query("DELETE FROM products_table")
    suspend fun dropDatabase()

    @Query("UPDATE products_table SET quantity = :newQuantity WHERE uid = :productId")
    suspend fun updateQuantity(productId: Int, newQuantity: Int)

    @Query("SELECT * FROM products_table WHERE name = :name")
    suspend fun getProductByName(name: String): Product?

}