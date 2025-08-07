package com.chicagojoker.myshop.domain.repository

import com.chicagojoker.myshop.domain.model.Product

interface ProductRepository {
    suspend fun getProducts() : List<Product>
}