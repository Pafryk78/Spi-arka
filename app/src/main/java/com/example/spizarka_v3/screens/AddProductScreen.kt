package com.example.spizarka_v3.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.spizarka_v3.MainViewModel
import com.example.spizarka_v3.data.Product

@Composable
fun AddProductScreen(
                     onNavigateBack: () -> Unit){
    val viewModel: MainViewModel = viewModel()
    var productName by remember { mutableStateOf("") }
    var productQuantity by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        OutlinedTextField(
            value = productName,
            onValueChange = { productName = it },
            label = { Text("Nazwa produktu") }
        )

        OutlinedTextField(
            value = productQuantity,
            onValueChange = { productQuantity = it},
            label = { Text("Ilość") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier.weight(1f)) // Wypełnia przestrzeń między polami a przyciskami

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Button(onClick = onNavigateBack) {
                Text("Anuluj")
            }

            Button(onClick = {
                // TODO: Walidacja danych
                val quantity = productQuantity.toIntOrNull() ?: 0 // Konwersja na Int, domyślnie 0
                val product = Product(name = productName, quantity = quantity)
                viewModel.addProduct(product)
                onNavigateBack()
            }) {
                Text("Dodaj")
            }
        }
    }
}