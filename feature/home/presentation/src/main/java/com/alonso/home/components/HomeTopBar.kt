package com.alonso.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import com.alonso.home.CategoryOption

@Composable
internal fun HomeTopBar(
    modifier: Modifier = Modifier,
    categories: List<CategoryOption>,
    selectedCategory: String,
    showBanner: Boolean,
    onClick: (CategoryOption) -> Unit
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Header(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 16.dp),
            title = "Coffee Go",
        )
        BannerImage(
            modifier = Modifier.padding(start = 12.dp, end = 12.dp, bottom = 16.dp),
            show = showBanner,
            title = "YOUR DAILY \nCOFFEE RITUAL STARTS HERE",
            image = R.drawable.cove_banner_coffe
        )
        Text(
            "Categories",
            modifier = Modifier.padding(horizontal = 12.dp),
            style = TextStyle(
                color = AppTheme.colors.textColor,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily(Font(R.font.roboto_flex))
            )
        )
        LazyRow(
            modifier = Modifier
                .padding(vertical = 8.dp)
        ) {
            items(categories) {
                CategoryOption(
                    text = it.name,
                    isSelected = selectedCategory == it.id,
                    onClick = { onClick(it) }
                )
            }
        }
    }
}
