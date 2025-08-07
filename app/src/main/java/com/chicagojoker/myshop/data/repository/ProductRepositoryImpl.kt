package com.chicagojoker.myshop.data.repository

import com.chicagojoker.myshop.data.remote.api.ProductApi
import com.chicagojoker.myshop.domain.model.Product
import com.chicagojoker.myshop.domain.model.Rating
import com.chicagojoker.myshop.domain.repository.ProductRepository

class ProductRepositoryImpl(
    private val api: ProductApi
) : ProductRepository {
    override suspend fun getProducts(): List<Product> {
        return api.getProducts().map { dto ->
            Product(
                id = dto.id,
                title = dto.title,
                description = dto.description,
                price = dto.price,
                category = dto.category,
                image = dto.image,
                rating = Rating(
                    rate = dto.rating.rate,
                    count = dto.rating.count
                )
            )
        }
    }

}