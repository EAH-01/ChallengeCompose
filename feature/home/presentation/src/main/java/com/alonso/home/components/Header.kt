package com.alonso.home.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.alonso.designsystem.AppTheme
import com.alonso.designsystem.R

@Composable
fun Header(
    modifier: Modifier = Modifier,
    title: String,
) {

    Text(
        text = title,
        modifier = modifier,
        style = TextStyle(
            color = AppTheme.colors.headerHomeTitle,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily(Font(R.font.roboto_flex))
        )
    )


}