package com.chicagojoker.myshop.presentation.cart

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.RemoveCircleOutline
import androidx.compose.material.icons.outlined.RemoveCircleOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.chicagojoker.myshop.domain.model.CartItem
import com.chicagojoker.myshop.presentation.home.ProductViewModel

@Composable
fun CartItemCard(item: CartItem, viewModel: ProductViewModel) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Row(modifier = Modifier.padding(12.dp)) {
            // Image
            AsyncImage(
                model = item.product.image,
                contentDescription = item.product.title,
                modifier = Modifier
                    .size(64.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(Modifier.width(12.dp))

            // Title and Description
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(item.product.title, style = MaterialTheme.typography.bodyMedium)
                item.product.description.takeIf { it.isNotEmpty() }?.let {
                    Text(it.take(30) + "...", style = MaterialTheme.typography.bodySmall)
                }
                Text(
                    "$${item.product.price}",
                    color = Color.Blue,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            // Quantity selector
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { viewModel.decreaseQuantity(item.product) }) {
                    Icon(imageVector = Icons.Outlined.RemoveCircleOutline, contentDescription = "Decrease")
                }
                Text("${item.quantity}")
                IconButton(onClick = { viewModel.increaseQuantity(item.product) }) {
                    Icon(Icons.Default.Add, contentDescription = "Increase")
                }
                IconButton(onClick = { viewModel.removeFromCart(item.product) }) {
                    Icon(Icons.Default.Delete, contentDescription = "Remove")
                }
            }
        }
    }
}
