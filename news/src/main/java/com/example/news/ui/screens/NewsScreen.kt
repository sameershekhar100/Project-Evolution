package com.example.news.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.news.ui.navgraphs.HomeNavGraph
import com.example.news.ui.route.NewsRoute

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NewsScreen(navController: NavHostController = rememberNavController()) {
    Scaffold(bottomBar = { CreateBottomBar(navController) })
    {
        Box(
            modifier = Modifier.padding(
                PaddingValues(bottom = it.calculateBottomPadding())
            )
        ) {
            HomeNavGraph(navController = navController)
        }
    }
}

@Composable
fun CreateBottomBar(navController: NavHostController) {
    val items = listOf(
        NewsRoute.Home,
        NewsRoute.Saved
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar {
        CreateBottomNavItem(items, currentDestination, navController)
    }

}

@Composable
fun RowScope.CreateBottomNavItem(
    items: List<NewsRoute>,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    items.forEach { item ->
        NavigationBarItem(
            selected = (currentDestination?.hierarchy?.any {
                it.route == item.route
            } == true),

            onClick = {
                navController.navigate(item.route) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            },
            label = { Text(text = item.title) },
            icon = {
                Icon(
                    imageVector = (
                            if (currentDestination?.route == item.route)
                                getIconFromDrawable(res = item.selectedIcon)
                            else getIconFromDrawable(res = item.unselectedIcon)
                            ),
                    contentDescription = item.title
                )
            }
        )
    }
}


@Composable
fun getIconFromDrawable(res: Int): ImageVector {
    return ImageVector.vectorResource(id = res)
}
object Graph {
    const val Root = "root_graph"
}