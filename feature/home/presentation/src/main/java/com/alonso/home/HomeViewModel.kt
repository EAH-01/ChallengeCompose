package com.alonso.home

import android.util.Log
import com.alonso.domain.getResult
import com.alonso.domain.usecase.HomeUseCase
import com.alonso.ui_components.base.BaseViewModel
import com.alonso.ui_components.base.Loader
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    loader: Loader,
    private val homeUseCase: HomeUseCase
) : BaseViewModel(loader) {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    init {
        getCoffeeByCategory("all")
    }

    private fun getCoffeeByCategory(categoryId: String) {
        launchIO(
            task = {
                _uiState.update { it.copy(isLoading = true) }
                delay(1000)
                homeUseCase(categoryId).getResult(
                    onSuccess = { data ->
                        data?.let { result ->
                            _uiState.update { it.copy(coffeeList = result.toCoffeeItem()) }
                        }
                    },
                    onError = {
                        Log.d("HOME_VIEW_MODEL", "Error: $it")
                    }
                )
            }, onCompletion = {
                _uiState.update { it.copy(isLoading = false) }
            }
        )
    }

    fun onSelectCategory(categoryId: String) {
        getCoffeeByCategory(categoryId = categoryId)
        _uiState.update { it.copy(selectedCategory = categoryId) }
    }
}
