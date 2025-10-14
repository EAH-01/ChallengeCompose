package com.alonso.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.alonso.designsystem.R

@Composable
fun Header(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = "PICK YOUR COFFEE",
            style = TextStyle(
                color = Color(0xFF2D231B),
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
        )
        Text(
            "Pick Up in store within 30 minutes",
            style = TextStyle(
                color = Color(0xFF655541),
                fontSize = 14.sp,
                fontWeight = FontWeight.W400,
                fontFamily = FontFamily(Font(R.font.roboto_flex))
            )
        )
    }
}