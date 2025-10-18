package com.alonso.detail

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.alonso.designsystem.AppTheme
import com.alonso.detail.components.CoffeeListSlider
import com.alonso.detail.components.CofferDetailsCard
import com.alonso.navigation.CoffeeItem


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    listCoffee: List<CoffeeItem>,
    coffeeClicked: Int = 0,
) {

    val scrollState = rememberScrollState()
    val currentCoffee = remember(listCoffee, coffeeClicked) {
        mutableStateOf(
            listCoffee.getOrNull(coffeeClicked) ?: listCoffee.firstOrNull()
        )
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = AppTheme.colors.backgroundDetails
    ) { innerPadding ->
        Column(
            modifier = modifier
                .verticalScroll(scrollState)
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            CoffeeListSlider(
                modifierParent = Modifier
                    .zIndex(2f)
                    .offset(y = (-50).dp),
                coffeeList = listCoffee,
                coffeeClicked = coffeeClicked,
                currentPage = { currentCoffee.value = it }
            )

            CofferDetailsCard(
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .zIndex(1f)
                    .offset(y = (-65).dp),
                drink = currentCoffee.value
            )
        }
    }
}


@OptIn(ExperimentalSharedTransitionApi::class)
@Preview(showBackground = true, name = "Full Screen")
@Composable
private fun DetailScreenPreview() {
    DetailScreen(
        coffeeClicked = 0,
        listCoffee = listOf(
            CoffeeItem(
                id = 1,
                name = "Espresso",
                price = 2.50,
                description = "Un shot concentrado de café con un sabor intenso y una crema rica.",
                image = "https://static.vecteezy.com/system/resources/previews/048/095/748/non_2x/shot-of-rich-espresso-with-a-creamy-top-png.png",
                qualification = 5
            )
        )
    )
}

