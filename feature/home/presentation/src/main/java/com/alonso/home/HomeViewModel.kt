package com.alonso.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alonso.domain.getResult
import com.alonso.domain.usecase.HomeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    homeUseCase: HomeUseCase
) : ViewModel() {

    init {
        viewModelScope.launch {
            homeUseCase("").getResult(
                onSuccess = {
                    Log.d("HOME_VIEW_MODEL", "Success: ${it?.joinToString()}")
                },
                onError = {
                    Log.d("HOME_VIEW_MODEL", "Error: $it")
                }
            )
        }
    }
}
