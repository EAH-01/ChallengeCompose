package com.alonso.challengecompose.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.ViewCompat
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

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            (view.context as Activity).window.statusBarColor = colors.backgroundHome.toArgb()
            ViewCompat.getWindowInsetsController(view)?.isAppearanceLightStatusBars = !darkTheme
        }
    }
    CompositionLocalProvider(
        LocalCustomColors provides colors,
        LocalComposeNavigator provides appNavigator,
        LocalCustomTypography provides TypographyCoffee,
        content = content
    )
}