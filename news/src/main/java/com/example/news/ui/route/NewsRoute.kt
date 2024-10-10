package com.example.news.ui.route

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource

sealed class NewsRoute(
    val route: String,
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
) {
    data object Home : NewsRoute(
        route = "Home",
        title = "Headlines",
        selectedIcon = Icons.Filled.Star,
        unselectedIcon = Icons.Outlined.Star
    )

    data object Saved : NewsRoute(
        route = "Saved",
        title = "Saved",
        selectedIcon = Icons.Filled.List,
        unselectedIcon = Icons.Outlined.List

    )
}
@Composable
fun getImage(res:Int):ImageVector{
    return ImageVector.vectorResource(id = res)
}