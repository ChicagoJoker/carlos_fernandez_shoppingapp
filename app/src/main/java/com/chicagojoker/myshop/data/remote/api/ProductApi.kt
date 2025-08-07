package com.chicagojoker.myshop.data.remote.api

import com.chicagojoker.myshop.data.remote.model.ProductDto
import retrofit2.http.GET


interface ProductApi {
    @GET("products")
    suspend fun getProducts(): List<ProductDto>
}