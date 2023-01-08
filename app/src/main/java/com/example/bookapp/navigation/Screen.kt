package com.example.bookapp.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash_screen")
    object Welcome : Screen("welcome_screen")
    object Category : Screen("category_screen")
    object Home : Screen("home_screen")
    object Details : Screen("details_screen/{bookId}") {
        fun passBookId(bookId: Int): String {
            return "details_screen/$bookId"
        }
    }

    object Search : Screen("search_screen")

    //jetpack
    object Jetpack : Screen("jetpack_screen")
    object DetailsJetpack : Screen("jetpackdetails_screen/{jetId}") {
        fun passJetId(jetId: Int): String {
            return "jetpackdetails_screen/$jetId"
        }
    }
    object JetpackSearch : Screen("jetpack_search")

    // XML
    object XmlScreen : Screen("xml_screen")
    object XmlDetails : Screen("xmldetails_screen/{xmlId}") {
        fun passXmlId(xmlId: Int): String {
            return "xmldetails_screen/$xmlId"
        }
    }
    object XmlSearch : Screen("jetpack_search")


}


