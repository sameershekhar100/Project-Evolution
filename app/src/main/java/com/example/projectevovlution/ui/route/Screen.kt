package com.example.projectevovlution.ui.route

sealed class Screen(val route:String) {
    object MainScreen : Screen("main_screen")
    object DetailScreen : Screen("detail_screen")
    object CalculatorScreen : Screen("calculator_screen")

    fun withData(vararg data : String) =
        buildString {
            append(route)
            data.forEach {
                append("/$it")
            }
        }
}