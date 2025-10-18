package com.alonso.designsystem


import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Immutable
data class CustomColors(
    val content: Color,
    val backgroundHome: Color,
    val backgroundDetails: Color,
    val textColor: Color
)

@Immutable
data class CustomTypography(
    val headlineMedium: TextStyle,
    val bodyMedium: TextStyle
)


val LightColors = CustomColors(
    content = Color.Blue,
    backgroundDetails = Color(0xFFDECFC8),
    backgroundHome = Color(0xFFFEFEFC),
    textColor = Color.Black
)
/* background = Color(0xFFFEF2E6),*/
val DarkColors = CustomColors(
    content = Color.White,
    backgroundDetails = Color.Black,
    backgroundHome = Color.Black,
    textColor = Color.White
)
val TypographyCoffee = CustomTypography(
    headlineMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        lineHeight = 32.sp,
        letterSpacing = 0.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.25.sp
    ),
)

val LocalCustomColors: ProvidableCompositionLocal<CustomColors> =
    compositionLocalOf {
        error(
            "No AppComposeTheme provided! " +
                    "Make sure to wrap all usages of app components in CustomColors.",
        )
    }

val LocalCustomTypography: ProvidableCompositionLocal<CustomTypography> =
    compositionLocalOf {
        error(
            "No LocalCustomTypography provided! " +
                    "Make sure to wrap all usages of app components in CustomTypography.",
        )
    }

object AppTheme {

    val typography: CustomTypography
        @Composable
        get() = LocalCustomTypography.current


    val colors: CustomColors
        @Composable
        @ReadOnlyComposable
        get() = LocalCustomColors.current
}