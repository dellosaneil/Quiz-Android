package com.thelazybattley.joserizalquiz.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.thelazybattley.quiz.quiz.ui.QuizScreen

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = AppScreens.QuizScreen.route
    ) {
        composable(route = AppScreens.QuizScreen.route) {
            QuizScreen()
        }
    }

}
