package com.example.spizarka_v3

import android.content.Context
import com.example.spizarka_v3.data.Product
import com.example.spizarka_v3.data.ProductDao
import com.example.spizarka_v3.data.ProductDb
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class Repository(context: Context): ProductDao {

    private val dao = ProductDb.getInstance(context).productDao()

    override suspend fun addProduct(product: Product) {
        dao.addProduct(product)
    }

    override suspend fun insertAll(products: kotlin.collections.List<com.example.spizarka_v3.data.Product>) = withContext(Dispatchers.IO) {
        dao.insertAll(products)
    }

    override suspend fun delete(products: List<Product>) = withContext(Dispatchers.IO){
    dao.delete(products)
    }

    override suspend fun update(product: Product) = withContext(Dispatchers.IO) {
        dao.update(product)
    }

    override fun getAll(): Flow<List<Product>> {
        return dao.getAll()
    }

    override suspend fun dropDatabase() = withContext(Dispatchers.IO) {
        dao.dropDatabase()
    }

}