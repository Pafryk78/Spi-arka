package com.example.spizarka_v3.screens

import androidx.activity.viewModels
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.spizarka_v3.MainViewModel
import com.example.spizarka_v3.data.Product
import com.example.spizarka_v3.product1


@Composable
fun HomeScreen( onClick: (String) -> Unit) {
    val viewModel: MainViewModel = viewModel()
    val products =  viewModel.getProducts().collectAsState(initial = emptyList())


    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            FloatingActionButton(onClick = { onClick("home") },
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
                ) {
                Icon(imageVector = Icons.Filled.Home, contentDescription = null)
            }
            FloatingActionButton(onClick = { onClick("ListaZakupow") },
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
            ) {
                Icon(imageVector = Icons.Filled.ShoppingCart, contentDescription = null)
            }
        }


        Box(modifier = Modifier.fillMaxSize()) {
            ProductList(products = products.value)
            FloatingActionButton(
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.BottomEnd),
                onClick = { onClick("AddProduct") }) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = null)
            }
        }
    }
}

@Composable
fun ProductList(products: List<Product>) {
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ProductLazyColumn(products)
    }
}

@Composable
fun ProductLazyColumn(products: List<Product>) {
    LazyColumn {
        items(items = products, key = { it.uid }) {
                product ->
            ProductRow(product)
        }
    }
}

@Composable
fun ProductRow(product: Product) {
    Surface(modifier = Modifier
        .fillMaxWidth()
        .padding(3.dp),
        shape= RoundedCornerShape(10.dp),
        shadowElevation =5.dp)
    {
        Row(modifier = Modifier.padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(text = "${product.name}",fontSize = 18.sp)

            Row(modifier = Modifier.padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                FloatingActionButton(
                    onClick = { },
                ) {
                    Icon(imageVector = Icons.Filled.ArrowDropDown, contentDescription = null)
                }
                Spacer(modifier = Modifier.width(25.dp))
                Text(text = "Ilość: ${product.quantity}",fontSize = 15.sp)
                Spacer(modifier = Modifier.width(25.dp))
                FloatingActionButton(
                    onClick = { },
                ) {
                    Icon(imageVector = Icons.Filled.KeyboardArrowUp, contentDescription = null)
                }
            }


        }
    }
}



@Preview(showBackground = true)
@Composable
fun ProductRowPreview() {
    ProductRow(product = product1)
}

