package com.alonso.detail

import androidx.compose.runtime.Composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.alonso.designsystem.R
import com.alonso.detail.components.CoffeeListSlider
import com.alonso.detail.components.CofferDetailsCard
import com.alonso.detail.components.DrinkItem


@Composable
fun DetailScreen(modifier: Modifier = Modifier) {
    val imageList = remember {
        listOf(
            R.drawable.coffee_starbuck,
            R.drawable.coffee_starbuck,
            R.drawable.coffee_starbuck,
            R.drawable.coffee_starbuck,
            R.drawable.coffee_starbuck,
            R.drawable.coffee_starbuck,
            R.drawable.coffee_starbuck,
            R.drawable.coffee_starbuck,
            R.drawable.coffee_starbuck,
            R.drawable.coffee_starbuck,
            R.drawable.coffee_starbuck,
        )
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.Blue
    ) { innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(Color.Black)
        ) {
            CoffeeListSlider(
                modifier = Modifier.zIndex(2f),
                imageList = imageList
            )

            CofferDetailsCard(
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .zIndex(1f)
                    .offset(y = (-30).dp),
                drink = DrinkItem(
                    id = "1",
                    name = "Strawberry Cobbler Drink",
                    price = "$13",
                    volume = "345ml",
                    description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Lorem ipsum dolor sit amet, consectetur adipiscing elit"
                ),
                onOrderClick = {},
            )
        }
    }
}


@Preview(showBackground = true, name = "Full Screen")
@Composable
private fun DetailScreenPreview() {
    DetailScreen()
}

