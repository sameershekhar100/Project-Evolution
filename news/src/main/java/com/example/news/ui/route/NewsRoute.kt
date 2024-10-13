package com.example.news.ui.route

import com.example.news.R

sealed class NewsRoute(
    val route: String,
    val title: String,
    val selectedIcon: Int,
    val unselectedIcon: Int
) {
    data object Home : NewsRoute(
        route = "Home",
        title = "Headlines",
        selectedIcon = R.drawable.baseline_star_24,
        unselectedIcon = R.drawable.baseline_star_outline_24
    )

    data object Saved : NewsRoute(
        route = "Saved",
        title = "Saved",
        selectedIcon = R.drawable.baseline_save_24,
        unselectedIcon = R.drawable.outline_save_24

    )
}