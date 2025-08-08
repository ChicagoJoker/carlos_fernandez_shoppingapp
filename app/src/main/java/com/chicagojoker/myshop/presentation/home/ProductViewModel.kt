package com.chicagojoker.myshop.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chicagojoker.myshop.domain.model.CartItem
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

    private val _cartItems = MutableStateFlow<List<CartItem>>(emptyList())
    val cartItems: StateFlow<List<CartItem>> = _cartItems

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
        val currentItems = _cartItems.value.toMutableList()
        val index = currentItems.indexOfFirst { it.product.id == product.id }

        if (index >= 0) {
            // Increment quantity
            val existingItem = currentItems[index]
            currentItems[index] = existingItem.copy(quantity = existingItem.quantity + 1)
        } else {
            // Add new item
            currentItems.add(CartItem(product = product, quantity = 1))
        }

        _cartItems.value = currentItems
    }

    fun removeFromCart(product: Product) {
        val currentItems = _cartItems.value.toMutableList()
        val index = currentItems.indexOfFirst { it.product.id == product.id }
        if (index >= 0) {
            currentItems.removeAt(index)
        }
        _cartItems.value = currentItems
    }

    fun increaseQuantity(product: Product) {
        val currentItems = _cartItems.value.toMutableList()
        val index = currentItems.indexOfFirst { it.product.id == product.id }

        if (index >= 0) {
            val existingItem = currentItems[index]
            currentItems[index] = existingItem.copy(quantity = existingItem.quantity + 1)
            _cartItems.value = currentItems
        }

    }
    fun decreaseQuantity(product: Product) {
        val currentItems = _cartItems.value.toMutableList()
        val index = currentItems.indexOfFirst { it.product.id == product.id }

        if (index >= 0) {
            val existingItem = currentItems[index]
            if(existingItem.quantity > 1)
            {
                currentItems[index] = existingItem.copy(quantity = existingItem.quantity - 1)
            }
            else
            {
                currentItems.removeAt(index)
            }
            _cartItems.value = currentItems
        }
    }


}