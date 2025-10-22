package com.alonso.home

import androidx.activity.compose.BackHandler
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.alonso.designsystem.AppTheme
import com.alonso.home.components.CoffeeCard
import com.alonso.home.components.HomeTopBar
import com.alonso.home.components.LoadCoffeeList
import com.alonso.navigation.AppNavigator
import com.alonso.navigation.AppScreen
import com.alonso.navigation.navRoot

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    appNavigator: AppNavigator = navRoot,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value
    val lazyGridState = rememberLazyGridState()

    BackHandler {
        appNavigator.clearBackStack()
    }
    Scaffold(
        modifier = modifier.navigationBarsPadding(),
        containerColor = AppTheme.colors.backgroundHome,
        topBar = {
            HomeTopBar(
                modifier = Modifier.padding(top = 22.dp),
                categories = uiState.categories,
                selectedCategory = uiState.selectedCategory,
                showBanner = lazyGridState.isScrollingDown().value.not(),
                onClick = { viewModel.onSelectCategory(it.id) }
            )
        }
    ) { innerPadding ->

        if (uiState.isLoading) {
            LoadCoffeeList(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize(), lazyGridState = lazyGridState
            )
        } else {
            LazyVerticalGrid(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(end = 12.dp),
                state = lazyGridState,
            ) {

                items(uiState.coffeeList, key = { it.id }) { coffee ->
                    CoffeeCard(
                        modifierParent = Modifier.padding(start = 12.dp, top = 12.dp),
                        coffeeName = coffee.name,
                        price = coffee.price.toString(),
                        onClick = {
                            appNavigator.goTo(
                                AppScreen.Detail(
                                    coffeeClicked = uiState.coffeeList.indexOf(coffee),
                                    listCoffee = uiState.coffeeList
                                )
                            )
                        },
                        imageUrl = coffee.image
                    )
                }
            }
        }
    }
}

@Composable
private fun LazyGridState.isScrollingDown() = remember {
    derivedStateOf {
        firstVisibleItemIndex > 0 ||
                firstVisibleItemScrollOffset > 0
    }
}


@OptIn(ExperimentalSharedTransitionApi::class)
@Preview(showBackground = true, name = "Full Screen")
@Composable
private fun HomeScreenPreview() {
    HomeScreen()
}
