package com.chicagojoker.myshop.presentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBarDefaults

import com.chicagojoker.myshop.domain.model.Product
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: ProductViewModel,
    onProductClick: (Int) -> Unit,
    onAddToCartClick: () -> Unit,
    onFilterClick: () -> Unit
) {
    val state by viewModel.uiState.collectAsState()

    when (state) {
        is ProductUiState.Loading -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        is ProductUiState.Success -> {
            val allProducts = (state as ProductUiState.Success).products
            val selectedCategory by viewModel.selectedCategory.collectAsState()
            val filteredProducts = if (selectedCategory != null) {
                allProducts.filter { it.category == selectedCategory }
            } else {
                allProducts
            }
            val categories = allProducts.map { it.category }.distinct()

            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .windowInsetsPadding(WindowInsets.statusBars)
            ) {
                CenterAlignedTopAppBar(
                    title = { Text("MyShop") },
                    actions = {
                        IconButton(onClick = onAddToCartClick) {
                            Icon(
                                imageVector = Icons.Default.ShoppingCart,
                                contentDescription = "Cart"
                            )
                        }
                    },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors()
                )
                // Category filter
                LazyRow(modifier = Modifier.padding(8.dp)) {
                    item {
                        Button(
                            onClick = { viewModel.selectCategory(null) },
                            enabled = selectedCategory != null,
                            modifier = Modifier.padding(horizontal = 4.dp)
                        ) {
                            Text("All")
                        }
                    }
                    items(categories) { category ->
                        Button(
                            onClick = { viewModel.selectCategory(category) },
                            enabled = selectedCategory != category,
                            modifier = Modifier.padding(horizontal = 4.dp)
                        ) {
                            Text(category.replaceFirstChar { it.uppercaseChar() })
                        }
                    }
                }

                // Product grid
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(minSize = 150.dp),
                    contentPadding = PaddingValues(8.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(filteredProducts) { product : Product ->
                        ProductItem(
                            product = product,
                            onClick = { onProductClick(product.id)},
                            onAddToCartClick = { viewModel.addToCart(product) }
                        )
                    }
                }
            }
        }

        is ProductUiState.Error -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text((state as ProductUiState.Error).message)
            }
        }
    }
}
