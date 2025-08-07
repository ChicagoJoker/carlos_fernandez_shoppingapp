package com.chicagojoker.myshop.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.chicagojoker.myshop.domain.model.Product

@Composable
fun ProductItem(product: Product, onClick: () -> Unit, onAddToCartClick: () -> Unit) {
    Card (
        onClick = onClick,
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column (modifier = Modifier.padding(8.dp)) {
            AsyncImage(
                model = product.image,
                contentDescription = product.title,
                modifier = Modifier
                    .height(120.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Fit
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(product.title, maxLines = 2, fontWeight = FontWeight.Bold)
            Text("$${product.price}", color = Color.Blue)
            Spacer(modifier = Modifier.height(4.dp))
            Button(
                onClick = onAddToCartClick,
                modifier = Modifier.padding(top = 8.dp)
            ) {
                Text("Add to Cart")
            }
        }
    }
}