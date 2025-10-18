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
    homeUseCase: HomeUseCase
) : BaseViewModel(loader) {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    init {
        launchIO(
            task = {
                _uiState.update { it.copy(isLoading = true) }
                delay(2000)
                homeUseCase("").getResult(
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

    fun onSelectCategory(category: String) {
        _uiState.update { it.copy(selectedCategory = category) }
    }
}
