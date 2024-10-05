package com.example.news.ui.screens

import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.news.R
import com.example.news.ui.navgraphs.HomeNavGraph

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NewsScreen(navController: NavController = rememberNavController()) {
    val items = listOf(
        BottomNavigationItem(
            title = "Headlines",
            selectedIcon = Icons.Filled.Star,
            unselectedIcon = Icons.Outlined.Star
        ), BottomNavigationItem(
            title = "Saved",
            selectedIcon = ImageVector.vectorResource(id = R.drawable.baseline_save_24),
            unselectedIcon = ImageVector.vectorResource(id = R.drawable.outline_save_24)
        )
    )
    Scaffold(
        bottomBar = { CreateBottomBar(items = items) }
    )
    {
        HomeNavGraph(navController = navController)
    }
}

@Composable
fun CreateBottomBar(items: List<BottomNavigationItem>) {
    var selectedItem by rememberSaveable { mutableIntStateOf(0) }
    NavigationBar {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedItem == index,
                onClick = {
                    selectedItem = index
                    //navigation.navigate(item.title)
                },
                label = { Text(text = item.title) },
                icon = {
                    Icon(
                        imageVector = (if (selectedItem == index) item.selectedIcon else item.unselectedIcon),
                        contentDescription = item.title
                    )
                })
        }
    }
}

data class BottomNavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
)