package com.alonso.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.alonso.home.CategoryOption

@Composable
internal fun HomeTopBar(
    modifier: Modifier = Modifier,
    categories: List<CategoryOption>,
    selectedCategory: String,
    onClick: (CategoryOption) -> Unit
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Header(
            modifier = Modifier.padding(horizontal = 12.dp),
            title = "Coffee Go",
            subtitle = "Your daily coffee ritual starts here"
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
