package com.alonso.search

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.alonso.designsystem.AppTheme
import com.alonso.navigation.AppNavigator
import com.alonso.navigation.AppScreen
import com.alonso.navigation.navRoot
import com.alonso.search.components.SearchBar
import com.alonso.ui_components.components.PrimaryCoffeeCard

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    appNavigator: AppNavigator = navRoot,
    viewModel: SearchViewModel = hiltViewModel()
) {
    val uiState = viewModel.state.collectAsStateWithLifecycle().value
    uiState.coffeeToSearch.useDebounce { viewModel.searchCoffeeByName(it) }
    Scaffold(
        topBar = {
            SearchBar(
                textChange = { viewModel.setCoffeeToSearch(it) },
                modifier = Modifier.padding(12.dp),
                textValue = uiState.coffeeToSearch,
                onSearch = {}
            )
        },
        containerColor = AppTheme.colors.backgroundHome,
        modifier = Modifier
            .fillMaxSize()
            .safeContentPadding()
    ) { innerPadding ->

        LazyVerticalStaggeredGrid(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            columns = StaggeredGridCells.Fixed(2),
            state = rememberLazyStaggeredGridState(),
        ) {

            items(uiState.coffees, key = { it.id }) { coffee ->
                PrimaryCoffeeCard(
                    modifier = Modifier.padding(8.dp),
                    coffeeName = coffee.name,
                    price = coffee.price.toString(),
                    volume = coffee.volume,
                    qualification = coffee.qualification,
                    onClick = {
                        appNavigator.goTo(
                            AppScreen.Detail(
                                coffeeClicked = uiState.coffees.indexOf(coffee),
                                listCoffee = uiState.coffees
                            )
                        )
                    },
                    imageUrl = coffee.image
                )
            }
        }
    }
}