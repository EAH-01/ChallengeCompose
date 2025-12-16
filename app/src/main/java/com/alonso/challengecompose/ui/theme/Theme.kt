package com.alonso.challengecompose.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.ViewCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.alonso.designsystem.CoffeeGoThemeData
import com.alonso.designsystem.DarkColors
import com.alonso.designsystem.LightColors
import com.alonso.designsystem.LocalCoffeeGoTheme
import com.alonso.designsystem.TypographyCoffee
import com.alonso.navigation.AppNavigator
import com.alonso.navigation.LocalComposeNavigator
import com.alonso.ui_components.base.LocalThemeViewModel
import com.alonso.ui_components.base.ThemeViewModel


@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    appNavigator: AppNavigator,
    themeViewModel: ThemeViewModel,
    content: @Composable () -> Unit
) {
    val isDarkMode by themeViewModel.themeState.collectAsStateWithLifecycle()
    val colors = when {
        darkTheme || isDarkMode -> DarkColors
        else -> LightColors
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            (view.context as Activity).window.statusBarColor = colors.backgroundHome.toArgb()
            ViewCompat.getWindowInsetsController(view)?.isAppearanceLightStatusBars = !darkTheme
        }
    }
    CompositionLocalProvider(
        LocalCoffeeGoTheme provides CoffeeGoThemeData(colors, TypographyCoffee),
        LocalComposeNavigator provides appNavigator,
        LocalThemeViewModel provides themeViewModel,
        content = content
    )
}