package com.example.projectevovlution.ui.route

import android.util.Log
import com.example.projectevovlution.ui.route.Screen.CalculatorScreen
import com.example.projectevovlution.ui.route.Screen.MainScreen
import com.example.projectevovlution.ui.route.Screen.NewsScreen
import com.example.projectevovlution.ui.route.Screen.NotesScreen

sealed class Screen(val route: String) {
    data object MainScreen : Screen("main_screen")
    data object DetailScreen : Screen("detail_screen")
    data object CalculatorScreen : Screen("calculator_screen")
    data object NotesScreen : Screen("notes_screen")
    data object NewsScreen : Screen("news_screen")

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
    Log.d("zzzzz1",id)
    return when (id) {
        "main_screen" -> MainScreen
        "calculator_screen" -> CalculatorScreen
        "notes_screen" -> NotesScreen
        "news_screen" -> NewsScreen
        else -> CalculatorScreen
    }
}