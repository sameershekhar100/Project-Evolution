package com.example.projectevovlution.ui.route

import com.example.projectevovlution.ui.route.Screen.CalculatorScreen
import com.example.projectevovlution.ui.route.Screen.MainScreen
import com.example.projectevovlution.ui.route.Screen.NewsScreen
import com.example.projectevovlution.ui.route.Screen.NotesScreen

sealed class Screen(val route: String) {
    object MainScreen : Screen("main_screen")
    object DetailScreen : Screen("detail_screen")
    object CalculatorScreen : Screen("calculator_screen")
    object NotesScreen : Screen("notes_screen")
    object NewsScreen : Screen("news_screen")

    fun withData(vararg data: String) =
        buildString {
            append(route)
            data.forEach {
                append("/$it")
            }
        }
}

fun String.getScreenId(): Screen {
    val id = this
        .trim()
        .lowercase()
        .split(" ")
        .joinToString("_")
    return when (id) {
        "main_screen" -> MainScreen
        "calculator_screen" -> CalculatorScreen
        "notes_screen" -> NotesScreen
        "news_screen" -> NewsScreen
        else -> CalculatorScreen
    }
}