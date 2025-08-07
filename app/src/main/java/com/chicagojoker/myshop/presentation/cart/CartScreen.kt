package com.chicagojoker.myshop.presentation.cart

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.chicagojoker.myshop.domain.model.Product
import androidx.compose.foundation.lazy.items
import com.chicagojoker.myshop.presentation.home.ProductViewModel

@Composable
fun CartScreen(viewModel: ProductViewModel) {
    val cartItems by viewModel.cartItems.collectAsState()

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Your Cart", modifier = Modifier.padding(bottom = 8.dp))

        LazyColumn {
            items(cartItems) { product: Product ->
                Column(modifier = Modifier.padding(vertical = 8.dp)) {
                    Text(product.title)
                    Text("$${product.price}")
                }
            }
        }
    }
}
