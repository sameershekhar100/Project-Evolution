package com.example.projectevovlution.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mylibrary.NoteScreen
import com.example.news.ui.screens.NewsScreen
import com.example.projectevovlution.ui.route.Screen
import com.example.projectevovlution.ui.screen.calculatorscreen.CalculatorScreen
import com.example.projectevovlution.ui.screen.MainScreen

@Composable
fun NavController() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {
        composable(route = Screen.MainScreen.route) {
            MainScreen(navController)
        }
        composable(route = Screen.CalculatorScreen.route) {
            CalculatorScreen()
        }
        composable(
            route = Screen.NotesScreen.route
//            arguments = listOf(
//                navArgument("name") {
//                    type = NavType.StringType
//                    defaultValue = ""
//                    nullable = true
//                }
//            )
        )
        {
            NoteScreen()
        }

        composable(route = Screen.NewsScreen.route){
            NewsScreen()
        }
    }
}