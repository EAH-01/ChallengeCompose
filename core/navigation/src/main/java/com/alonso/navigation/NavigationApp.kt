package com.alonso.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import javax.inject.Inject


object App {
    val LocalComposeNavigator: ProvidableCompositionLocal<AppNavigator> =
        compositionLocalOf {
            error(
                "No AppComposeNavigator provided! " +
                        "Make sure to wrap all usages of app components in NavigationApp.",
            )
        }

    val navRoot: AppNavigator
        @Composable
        @ReadOnlyComposable
        get() = LocalComposeNavigator.current
}


abstract class AppNavigator {
    protected var navBackStack: NavBackStack<NavKey>? = null
    fun initialize(backStack: NavBackStack<NavKey>) {
        this.navBackStack = backStack
    }

    abstract fun goTo(key: NavKey, clearBackStack: Boolean = false)
    abstract fun popBack()
    abstract fun canGoBack(): Boolean
}


class CoffeeNavigator @Inject constructor() : AppNavigator() {

    override fun goTo(key: NavKey, clearBackStack: Boolean) {
        if (clearBackStack) navBackStack?.clear()
        navBackStack?.add(key)
    }

    override fun popBack() {
        navBackStack?.removeLastOrNull()
    }

    override fun canGoBack(): Boolean {
        return navBackStack?.size?.let { it > 1 } ?: false
    }
}