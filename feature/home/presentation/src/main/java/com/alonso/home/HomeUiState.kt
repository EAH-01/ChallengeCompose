package com.alonso.home

import com.alonso.navigation.CoffeeItem

data class HomeUiState(
    val coffeeList: List<CoffeeItem> = emptyList(),
    val isLoading: Boolean = false,
    val categories: List<String> = listOf("Recommendation", "Coffee", "Tea", "Smoothie"),
    val selectedCategory: String = "Recommendation"
)
