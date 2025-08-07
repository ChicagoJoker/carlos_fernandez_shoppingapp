package com.chicagojoker.myshop.presentation.productdetails

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.chicagojoker.myshop.presentation.home.ProductUiState
import com.chicagojoker.myshop.presentation.home.ProductViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailScreen(
    productId: Int,
    navController: NavController,
    viewModel: ProductViewModel
) {
    val state by viewModel.uiState.collectAsState()

    val product = if (state is ProductUiState.Success) {
        (state as ProductUiState.Success).products.find { it.id == productId }
    } else null

    product?.let {
        Column (modifier = Modifier.padding(16.dp)) {
            CenterAlignedTopAppBar(
                title = { Text("Product Detail", style = MaterialTheme.typography.titleMedium) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
            AsyncImage(
                model = it.image,
                contentDescription = it.title,
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth(),
                contentScale =  ContentScale.Fit
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(it.title, fontWeight = FontWeight.Bold)
            Text("$${it.price}", color = Color.Blue)
            Spacer(modifier = Modifier.height(8.dp))
            Text(it.description)
        }
    } ?: run {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Product not found")
        }
    }



}