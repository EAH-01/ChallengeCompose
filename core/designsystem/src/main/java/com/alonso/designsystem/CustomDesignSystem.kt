package com.alonso.designsystem


import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Immutable
data class CustomColors(
    val content: Color,
    val backgroundSplash: Color,
    val textIconSplash: Color,
    val backgroundHome: Color,
    val backgroundDetails: Color,
    val textColor: Color,
    val coffeeCardBackgroundPrimary: Color,
    val coffeeCardBackgroundSecondary: Color,
    val coffeeCardBorder: Color,
    val optionCategoryBorderEnabled: Color,
    val optionCategoryBackgroundEnabled: Color,
    val optionCategoryTextEnabled: Color,
    val optionCategoryBorderDisabled: Color,
    val optionCategoryBackgroundDisabled: Color,
    val optionCategoryTextDisabled: Color,
    val headerHomeTitle: Color,
    val headerHomeSubtitle: Color
)

@Immutable
data class CustomTypography(
    val headlineMedium: TextStyle,
    val bodyMedium: TextStyle
)


val LightColors = CustomColors(
    backgroundSplash = Color(0xFFF4F5F0),
    textIconSplash = Color(0xFF5A270D),

    content = Color.Blue,
    backgroundDetails = Color(0xFFDECFC8),
    backgroundHome = Color(0xFFFEFEFC),
    textColor = Color.Black,
    coffeeCardBackgroundPrimary = Color(0xFFFFFFFF),
    coffeeCardBackgroundSecondary = Color(0xFFF5F1EE),
    coffeeCardBorder = Color(0xFFF5F1EE),

    optionCategoryBorderEnabled = Color(0xFF6B4E3D),
    optionCategoryBackgroundEnabled = Color(0xFFF7F6F0),
    optionCategoryTextEnabled = Color(0xFF534D46),

    optionCategoryBorderDisabled = Color(0xFFE0E0E0),
    optionCategoryBackgroundDisabled = Color.Transparent,
    optionCategoryTextDisabled = Color(0xFF534D46),

    headerHomeTitle = Color(0xFF2D231B),
    headerHomeSubtitle = Color(0xFF655541)

)

val DarkColors = CustomColors(
    backgroundSplash = Color(0xFF0C1015),
    textIconSplash = Color(0xFFF69C44),

    content = Color.White,
    backgroundDetails = Color(0xFF0C1015),
    backgroundHome = Color(0xFF0C1015),
    textColor = Color.White,

    coffeeCardBackgroundPrimary = Color(0xFF272B30),
    coffeeCardBackgroundSecondary = Color(0xFF272B30),
    coffeeCardBorder = Color(0xFF272B30),

    optionCategoryBorderEnabled = Color(0xFFF69C44),
    optionCategoryBackgroundEnabled = Color(0xFFF69C44),
    optionCategoryTextEnabled = Color(0xFFFFFFFF),

    optionCategoryBorderDisabled = Color(0xFF0C1015),
    optionCategoryBackgroundDisabled = Color(0xFF0C1015),
    optionCategoryTextDisabled = Color(0xFFFFFFFF),

    headerHomeTitle = Color.White,
    headerHomeSubtitle = Color.White
)
val TypographyCoffee = CustomTypography(
    headlineMedium = TextStyle(
        fontWeight = FontWeight.Bold,
        fontFamily = FontFamily(Font(R.font.roboto_flex)),
        fontSize = 24.sp,
        lineHeight = 32.sp,
        letterSpacing = 0.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.roboto_flex)),
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