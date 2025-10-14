package com.alonso.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alonso.home.components.BannerImage
import com.alonso.home.components.CategoryOption
import com.alonso.home.components.CoffeeCard
import com.alonso.home.components.Header

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {

    val categories = listOf("Recommendation", "Coffee", "Tea", "Smoothie")
    var selectedCategory by remember { mutableStateOf("Coffee") }
    val lazyGridState = rememberLazyGridState()

    Scaffold(
        containerColor = Color.White,
        topBar = {
            Column(modifier = Modifier.fillMaxWidth()) {
                Header(
                    modifier = modifier.padding(12.dp),
                    title = "PICK YOUR COFFEE",
                    subtitle = "Pick Up in store within 30 minutes"
                )
                BannerImage(
                    modifier.padding(12.dp),
                )
                LazyRow(modifier = modifier.padding(bottom = 8.dp)) {
                    items(categories) { category ->
                        CategoryOption(
                            text = category,
                            isSelected = selectedCategory == category,
                            onClick = { selectedCategory = category }
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        LazyVerticalGrid(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(end = 12.dp),
            state = lazyGridState,
        ) {
            items(10) { index ->
                CoffeeCard(
                    modifier = Modifier.padding(start = 12.dp, top = 12.dp),
                    coffeeName = "Vietnamese Coconut Coffee",
                    price = "11",
                    imageRes = com.alonso.designsystem.R.drawable.coffee_starbuck
                )
            }
        }
    }
}


@Preview(showBackground = true, name = "Full Screen")
@Composable
private fun HomeScreenPreview() {
    HomeScreen()
}
