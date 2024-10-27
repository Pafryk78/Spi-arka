package com.example.spizarka_v3

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spizarka_v3.data.Category
import com.example.spizarka_v3.data.Product
import kotlinx.coroutines.launch

class ScreenViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = Repository(application.applicationContext)

    // ... inne funkcje i właściwości ViewModel

    val categories = listOf(
        Category(name = "Owoce", imageResId = R.drawable.owoce),
        Category(name = "Warzywa",imageResId = R.drawable.warzywa),
        Category(name = "Nabiał", imageResId = R.drawable.nabial),

    )
    fun updateProductQuantity(product: Product, quantityChange: Int) {
        viewModelScope.launch {
            val newQuantity = product.quantity + quantityChange
            if (newQuantity >= 0) {
                repository.updateQuantity(product.uid, newQuantity)
            }
        }
    }
}