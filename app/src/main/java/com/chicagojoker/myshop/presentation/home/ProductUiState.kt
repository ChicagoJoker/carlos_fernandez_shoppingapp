package com.chicagojoker.myshop.presentation.home

import com.chicagojoker.myshop.domain.model.Product

sealed class ProductUiState {
    data class Success(val products: List<Product>) : ProductUiState()
    data class Error(val message: String) : ProductUiState()
    object Loading : ProductUiState()
}