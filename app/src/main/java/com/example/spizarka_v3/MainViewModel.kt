package com.example.spizarka_v3

import android.app.Application
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spizarka_v3.data.Product
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class MainViewModel(app: Application) : AndroidViewModel(app) {
    private val repository = Repository(app.applicationContext)




    fun addProduct(product: Product) {
        viewModelScope.launch {
            repository.addProduct(product)

        }
    }


    fun getProducts(): Flow<List<Product>> {
        return repository.getAll()

    }

    init {
CoroutineScope(Dispatchers.IO).launch {
    //repository.dropDatabase()
    //populateDatabase()
}


    }
    private fun populateDatabase() {
        repeat(20) {
            val time = System.currentTimeMillis()
            val product = Product(name = "Product ${time % 100}", quantity = 1)
            CoroutineScope(viewModelScope.coroutineContext).launch {
                repository.insertAll(listOf(product))
            }
        }
    }
}