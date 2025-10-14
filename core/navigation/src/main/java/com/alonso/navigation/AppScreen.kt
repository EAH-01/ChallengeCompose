package com.alonso.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed interface AppScreen : NavKey {
    @Serializable
    data object Home : NavKey

    @Serializable
    data object Detail : NavKey
}