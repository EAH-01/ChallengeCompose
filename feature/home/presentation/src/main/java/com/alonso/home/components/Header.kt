package com.alonso.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alonso.designsystem.AppTheme
import com.alonso.designsystem.R

@Composable
fun Header(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = title,
            style = TextStyle(
                color = AppTheme.colors.headerHomeTitle,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily(Font(R.font.roboto_flex))
            )
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            subtitle,
            style = TextStyle(
                color = AppTheme.colors.headerHomeSubtitle,
                fontSize = 16.sp,
                fontWeight = FontWeight.W400,
                fontFamily = FontFamily(Font(R.font.roboto_flex))
            )
        )
    }
}