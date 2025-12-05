package com.alonso.presentation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.captionBarPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alonso.designsystem.AppTheme
import com.alonso.home.HomeScreen
import com.alonso.search.SearchScreen


@Composable
fun DashboardScreen() {

    var clickItemSelect by rememberSaveable { mutableStateOf(BottomNavItem.OverviewScreen.path) }
    Scaffold(
        modifier = Modifier.statusBarsPadding(),
        containerColor = AppTheme.colors.backgroundHome,
        bottomBar = { BottomNavigationBar { clickItemSelect = it } }
    ) {
        AnimatedContent(
            modifier = Modifier.padding(it),
            targetState = clickItemSelect,
            transitionSpec = {
                fadeIn(tween(durationMillis = 100)) togetherWith fadeOut(tween(durationMillis = 100))
            },
            label = "Content Animation"
        ) { itemSelect ->
            when (itemSelect) {
                BottomNavItem.OverviewScreen.path -> HomeScreen()
                BottomNavItem.FavoriteScreen.path -> EmptyScreen("Favorite")
                BottomNavItem.SearchScreen.path -> SearchScreen()
                BottomNavItem.SettingScreen.path -> EmptyScreen("Setting")

            }
        }
    }
}

@Composable
private fun BottomNavigationBar(
    onItemClick: (String) -> Unit,
) {
    var selectedItem by rememberSaveable { mutableIntStateOf(0) }
    val navItems = listOf(
        BottomNavItem.OverviewScreen,
        BottomNavItem.SearchScreen,
        BottomNavItem.FavoriteScreen,
        BottomNavItem.SettingScreen
    )

    NavigationBar(
        containerColor = AppTheme.colors.backgroundHome
    ) {
        navItems.forEachIndexed { index, item ->
            NavigationBarItem(
                alwaysShowLabel = true,
                icon = { HandleIconStyle(selectedItem, index, item) },
                label = { HandleLabelStyle(selectedItem, index, item) },
                selected = false,
                onClick = {
                    selectedItem = index
                    onItemClick(item.path)
                }
            )
        }
    }
}


@Composable
private fun HandleLabelStyle(selectedItem: Int, index: Int, item: BottomNavItem) {
    val color = if (selectedItem == index) AppTheme.colors.optionCategoryBackgroundEnabled
    else Color(0xFF878787)
    val fontWeight = if (selectedItem == index) FontWeight(800) else null
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = item.title,
            style = AppTheme.typography.commonRegularTextStyle.copy(
                color = color,
                fontSize = 10.sp,
                fontWeight = fontWeight
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(modifier = Modifier.height(6.dp))
        Box(
            modifier = Modifier
                .alpha(if (selectedItem == index) 1f else 0f)
                .width(10.dp)
                .height(2.dp)
                .background(color, shape = RoundedCornerShape(50))
        )
    }
}

@Composable
private fun HandleIconStyle(selectedItem: Int, index: Int, item: BottomNavItem) {
    val color = if (selectedItem == index) AppTheme.colors.optionCategoryBackgroundEnabled
    else Color(0xFF878787)
    Icon(
        painter = painterResource(id = item.icon),
        contentDescription = item.title,
        tint = color
    )
}

private fun DrawScope.drawTopBorder(color: Color, strokeWidth: Float) {
    drawLine(
        color = color,
        start = Offset(0f, 0f),
        end = Offset(size.width, 0f),
        strokeWidth = strokeWidth
    )
}

@Composable
fun EmptyScreen(title: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Yellow),
        contentAlignment = Alignment.Center
    ) {
        Text(text = title, modifier = Modifier.align(Alignment.Center))
    }
}