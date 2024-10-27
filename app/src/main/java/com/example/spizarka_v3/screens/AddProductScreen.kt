package com.example.spizarka_v3.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.ModalBottomSheet

import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.spizarka_v3.MainViewModel
import com.example.spizarka_v3.ScreenViewModel
import com.example.spizarka_v3.data.Category
import com.example.spizarka_v3.data.Product

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddProductScreen(
                     onNavigateBack: () -> Unit){
    val screenViewModel : ScreenViewModel = viewModel()
    val viewModel: MainViewModel = viewModel()

    var showCategoryList by remember { mutableStateOf(false) }
    var productName by remember { mutableStateOf("") }
    var productQuantity by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf<String?>(null) }
    val categories by remember { mutableStateOf(screenViewModel.categories) }
    var expanded by remember { mutableStateOf(false) }



    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

//        OutlinedTextField(
//            value = selectedCategory?.name ?: "",
//            onValueChange = { },
//            label = { Text("Kategoria") },
//            readOnly = true,
//            modifier = Modifier.clickable { showCategoryList = true } // Dodaj clickable
//        )
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            TextField(
                value = selectedCategory ?: "Wybierz kategorię",
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                colors = ExposedDropdownMenuDefaults.textFieldColors(),
                modifier = Modifier.menuAnchor()
            )

            // Niestandardowy DropdownMenu
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.exposedDropdownSize()
            ) {
                categories.forEach { category ->
                    CategoryItem(category) {
                        selectedCategory = category.name
                        expanded = false
                    }
                }
            }
        }






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
                val quantity = productQuantity.toIntOrNull() ?: 0 // Konwersja na Int, domyślnie 0
                val product = Product(category = selectedCategory.toString(),name = productName, quantity = quantity)
                viewModel.addProduct(product)
                onNavigateBack()
            }) {
                Text("Dodaj")
            }
        }
    }

    if (showCategoryList) {
        ModalBottomSheet(
            onDismissRequest = { showCategoryList = false }
        ) {
            LazyColumn {
                items(categories) { category ->
                    CategoryItem(category) {
                        selectedCategory = category.name
                        showCategoryList = false
                    }
                }
            }
        }
    }
}









@Composable
fun CategoryItem(category: Category, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = category.imageResId),
            contentDescription = "Category Image",
            modifier = Modifier.size(48.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(text =category.name)
    }
}