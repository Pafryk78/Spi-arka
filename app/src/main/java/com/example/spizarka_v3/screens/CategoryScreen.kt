package com.example.spizarka_v3.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.spizarka_v3.MainViewModel
import com.example.spizarka_v3.ScreenViewModel
import com.example.spizarka_v3.data.Category
import com.example.spizarka_v3.data.Product

@Composable
fun CategoryScreen(category: String, onIncreaseQuantity: (Product, Int) -> Unit, onAddProductClick: () -> Unit) {
    val viewModel: MainViewModel = viewModel()
    val products = viewModel.getProducts().collectAsState(initial = emptyList())
    var searchQuery by remember { mutableStateOf("") }
    val filteredProducts = if (searchQuery.isBlank()) {
        products.value.filter { product ->
            product.category == category
        }
    } else {
        products.value.filter { product ->
            product.category == category&& product.name.contains(searchQuery, ignoreCase = true)
        }
    }

    Column {

        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            label = { Text("Wyszukaj produkt") },
            modifier = Modifier.fillMaxWidth()
        )



        Box(modifier = Modifier.fillMaxSize()) {



            ProductList(
                filteredProducts = filteredProducts,
                onIncreaseQuantity = { product -> viewModel.increaseProductQuantity(product) },
                onDecreaseQuantity = { product -> viewModel.decreaseProductQuantity(product) }
            )
            FloatingActionButton(
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.BottomEnd),
                onClick = onAddProductClick) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = null)
            }
        }
    }
}






