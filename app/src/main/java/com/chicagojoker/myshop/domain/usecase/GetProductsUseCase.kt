package com.chicagojoker.myshop.domain.usecase

import com.chicagojoker.myshop.domain.model.Product
import com.chicagojoker.myshop.domain.repository.ProductRepository

class GetProductsUseCase(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(): List<Product> {
        return repository.getProducts()
    }
}