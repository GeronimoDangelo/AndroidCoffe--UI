package com.example.bookapp.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash_screen")
    object Welcome : Screen("welcome_screen")
    object Category: Screen("category_screen")
    object Home : Screen("home_screen")
    object Details : Screen("details_screen/{bookId}") {
        fun passBookId(bookId: Int): String {
            return "details_screen/$bookId"
        }
    }
    object Search : Screen("search_screen")
}


