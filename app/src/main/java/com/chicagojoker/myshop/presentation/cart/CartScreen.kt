package com.chicagojoker.myshop.presentation.cart

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import com.chicagojoker.myshop.domain.model.CartItem
import androidx.hilt.navigation.compose.hiltViewModel
import com.chicagojoker.myshop.domain.model.Product
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import com.chicagojoker.myshop.presentation.home.ProductViewModel

@Composable
fun CartScreen(viewModel: ProductViewModel) {
    val cartItems by viewModel.cartItems.collectAsState()
    val totalItems = cartItems.sumOf { it.quantity.toInt() }


    Column(modifier = Modifier.padding(16.dp)) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            Icon(Icons.Default.ShoppingCart, contentDescription = null, tint = Color.Red)
            Text(
                text = "$totalItems Items in your cart",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
        LazyColumn {
            items(cartItems) { cartItem ->
                CartItemCard(cartItem, viewModel)
            }
        }
    }
}
