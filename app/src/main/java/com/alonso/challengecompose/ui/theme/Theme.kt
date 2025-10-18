package com.alonso.challengecompose.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.alonso.designsystem.DarkColors
import com.alonso.designsystem.LightColors
import com.alonso.designsystem.LocalCustomColors
import com.alonso.designsystem.LocalCustomTypography
import com.alonso.designsystem.TypographyCoffee
import com.alonso.navigation.AppNavigator
import com.alonso.navigation.LocalComposeNavigator


@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    appNavigator: AppNavigator,
    content: @Composable () -> Unit
) {

    val colors = when {
        darkTheme -> DarkColors
        else -> LightColors
    }

    CompositionLocalProvider(
        LocalCustomColors provides colors,
        LocalComposeNavigator provides appNavigator,
        LocalCustomTypography provides TypographyCoffee,
        content = content
    )
}