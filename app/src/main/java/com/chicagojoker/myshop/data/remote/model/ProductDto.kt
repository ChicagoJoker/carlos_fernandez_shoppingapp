package com.chicagojoker.myshop.data.remote.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProductDto(
    val id: Int,
    val title: String,
    val description: String,
    val price: Double,
    val category: String,
    val image: String,
    val rating: RatingDto
)

@JsonClass(generateAdapter = true)
data class RatingDto(
    val rate: Double,
    val count: Int
)
