package com.chicagojoker.myshop.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chicagojoker.myshop.domain.model.Product
import com.chicagojoker.myshop.domain.usecase.GetProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase

) : ViewModel() {

    private val _uiState =  MutableStateFlow<ProductUiState>(ProductUiState.Loading)
    val uiState: StateFlow<ProductUiState> =  _uiState

    private val _selectedCategory = MutableStateFlow<String?>(null)
    val selectedCategory: StateFlow<String?> = _selectedCategory

    private val _cartItems = MutableStateFlow<List<Product>>(emptyList())
    val cartItems: StateFlow<List<Product>> = _cartItems

    init {
        println("🔥 ViewModel INIT")
        loadProducts()
    }

    private fun loadProducts() {
        viewModelScope.launch {
            try {
                val products =  getProductsUseCase()
                _uiState.value = ProductUiState.Success(products)
            } catch (e: Exception) {
                e.printStackTrace() // 🔍 print the real error to Logcat
                _uiState.value = ProductUiState.Error("Failed to load products")
            }
        }
    }

    fun selectCategory(category: String?) {
        _selectedCategory.value = category

    }

    fun addToCart(product: Product) {
        println("✅ Adding to cart: ${product.title}")
        _cartItems.value = _cartItems.value + product
    }

}