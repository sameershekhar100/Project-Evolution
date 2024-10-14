package com.example.news.ui.navgraphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.news.ui.route.NewsRoute
import com.example.news.ui.screens.Graph
import com.example.news.ui.screens.homescreen.HomeScreen
import com.example.news.ui.screens.SavedScreen

@Composable
fun HomeNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.Root,
        startDestination = NewsRoute.Home.route
    ){
        composable(route = NewsRoute.Home.route) {
            HomeScreen()
        }
        composable(route = NewsRoute.Saved.route) {
            SavedScreen()
        }
    }
}