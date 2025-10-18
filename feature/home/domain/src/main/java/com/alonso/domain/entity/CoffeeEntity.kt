package com.alonso.domain.entity

data class CoffeeEntity(
    val id: Int,
    val name: String,
    val price: Double,
    val description: String,
    val image: String,
    val qualification: Int
)
