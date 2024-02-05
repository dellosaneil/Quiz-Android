package com.thelazybattley.joserizalquiz.navigation

sealed class AppScreens(val route: String, val routeWithArgs: String = route) {

    data object QuizScreen : AppScreens(route = "quiz_screen")

}
