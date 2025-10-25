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
    val headerHomeSubtitle: Color,
    val grayCommon: Color,
    val backgroundNetworkDisconnected: Color
)

@Immutable
data class CustomTypography(
    val headlineMedium: TextStyle,
    val bodyMedium: TextStyle,
    val commonRegularTextStyle: TextStyle,
    val commonMediumTextStyle: TextStyle,
    val commonBoldTextStyle: TextStyle
)


val LightColors = CustomColors(
    backgroundSplash = Color(0xFFF9F4EA),
    textIconSplash = Color(0xFF5A270D),

    content = Color.Blue,
    backgroundDetails = Color(0xFFDECFC8),
    backgroundHome = Color(0xFFF9F4EA),
    textColor = Color.Black,
    coffeeCardBackgroundPrimary = Color(0xFFF9F4EA),
    coffeeCardBackgroundSecondary = Color(0xFFE6D3C7),
    coffeeCardBorder = Color(0xFFECE6E2),

    optionCategoryBorderEnabled = Color(0xFF7d4532),
    optionCategoryBackgroundEnabled = Color(0xFF7d4532),
    optionCategoryTextEnabled = Color.White,

    optionCategoryBorderDisabled = Color(0xFFE0E0E0),
    optionCategoryBackgroundDisabled = Color.Transparent,
    optionCategoryTextDisabled = Color(0xFF534D46),

    headerHomeTitle = Color(0xFF2D231B),
    headerHomeSubtitle = Color(0xFF655541),
    grayCommon = Color(0xFF92908e),
    backgroundNetworkDisconnected = Color(0x49e50a2a)

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
    headerHomeSubtitle = Color.White,
    grayCommon = Color.White,
    backgroundNetworkDisconnected = Color(0xFFFF4F6B)
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
    commonRegularTextStyle = TextStyle(
        fontSize = 12.sp,
        fontFamily = FontFamily(Font(R.font.roboto_flex)),
        fontWeight = FontWeight.Normal
    ),

    commonMediumTextStyle = TextStyle(
        fontSize = 12.sp,
        fontFamily = FontFamily(Font(R.font.roboto_flex)),
        color = Color.Black,
        fontWeight = FontWeight.Medium
    ),
    commonBoldTextStyle = TextStyle(
        fontSize = 12.sp,
        fontFamily = FontFamily(Font(R.font.roboto_flex)),
        color = Color.Black,
        fontWeight = FontWeight.Medium
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