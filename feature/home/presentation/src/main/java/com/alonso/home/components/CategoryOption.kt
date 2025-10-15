package com.alonso.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun CategoryOption(
    modifier: Modifier = Modifier,
    text: String,
    isSelected: Boolean = false,
    onClick: () -> Unit = {}
) {
    val backgroundColor = if (isSelected) Color(0xFFF7F6F0)
    else Color.Transparent

    val borderColor = if (isSelected) Color(0xFF6B4E3D)
    else Color(0xFFE0E0E0)

    val textColor = if (isSelected) Color(0xFF534D46) else Color(0xFF534D46)


    Text(
        modifier = modifier
            .padding(4.dp)
            .clip(RoundedCornerShape(24.dp))
            .clickable(
                role = Role.Button,
                onClick = onClick
            )
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(24.dp)
            )
            .border(
                width = 1.dp,
                color = borderColor,
                shape = RoundedCornerShape(24.dp)
            )
            .padding(horizontal = 16.dp, vertical = 8.dp),
        text = text,
        style = MaterialTheme.typography.bodyMedium.copy(
            color = textColor,
            fontWeight = if (isSelected) FontWeight.Medium else FontWeight.Normal
        )
    )
}


@Preview(showBackground = true)
@Composable
private fun CategoryOptionPreview() {
    CategoryOption(
        text = "Recommendation",
        isSelected = false,
        onClick = {}
    )

}


@Preview(showBackground = true)
@Composable
private fun CategoryOptionSelectedPreview() {
    CategoryOption(
        text = "Coffee",
        isSelected = true,
        onClick = {}
    )

}