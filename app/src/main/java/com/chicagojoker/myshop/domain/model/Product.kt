package com.chicagojoker.myshop.domain.model

data class Product(
    val id: Int,
    val title: String,
    val description: String,
    val price: Double,
    val category: String,
    val image: String,
    val rating: Rating
)

data class Rating(
    val rate: Double,
    val count: Int
)
