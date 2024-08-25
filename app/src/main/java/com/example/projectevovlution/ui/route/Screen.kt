package com.example.projectevovlution.ui.route

import android.util.Log
import com.example.projectevovlution.ui.route.Screen.CalculatorScreen
import com.example.projectevovlution.ui.route.Screen.MainScreen
import com.example.projectevovlution.ui.route.Screen.NotesScreen

sealed class Screen(val route: String) {
    object MainScreen : Screen("main_screen")
    object DetailScreen : Screen("detail_screen")
    object CalculatorScreen : Screen("calculator_screen")
    object NotesScreen : Screen("notes_screen")

    fun withData(vararg data: String) =
        buildString {
            append(route)
            data.forEach {
                append("/$it")
            }
        }
}

fun String.getStringId(): Screen {
    Log.d("zzzz",this.toString())
    val id = this
        .trim()
        .lowercase()
        .split(" ")
        .joinToString("_")
    Log.d("zzzz",id.toString())

    return when (id) {
        "main_screen" -> MainScreen
        "calculator_screen" -> CalculatorScreen
        "notes_screen" -> NotesScreen
        else -> CalculatorScreen
    }
}