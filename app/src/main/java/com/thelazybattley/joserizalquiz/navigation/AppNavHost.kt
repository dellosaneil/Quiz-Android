package com.thelazybattley.joserizalquiz.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.thelazybattley.common.model.AppScreens
import com.thelazybattley.common.model.AppScreens.Companion.QUIZ_RESULT_STATE
import com.thelazybattley.dashboard.dashboard.ui.DashboardScreen
import com.thelazybattley.quiz.quiz.AssetParamType
import com.thelazybattley.quiz.quiz.ui.QuizScreen
import com.thelazybattley.quiz.quizresult.ui.QuizResultScreen
import com.thelazybattley.quiz.reviewquiz.ui.ReviewQuizScreen

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = AppScreens.DashboardScreen.route
    ) {
        composable(route = AppScreens.QuizScreen.route) {
            QuizScreen(
                onPopBackStack = {
                    navController.popBackStack()
                }
            ) { route, navOptions ->
                navController.navigate(route = route, navOptions = navOptions)
            }
        }
        composable(
            route = AppScreens.QuizResultScreen.routeWithArgs,
            arguments = listOf(
                navArgument(name = QUIZ_RESULT_STATE) {
                    type = AssetParamType()
                }
            )
        ) {
            QuizResultScreen(
                onPopBackStack = {
                    navController.popBackStack()
                }
            ) { route, navOptions ->
                navController.navigate(route = route, navOptions = navOptions)
            }
        }
        composable(route = AppScreens.DashboardScreen.route) {
            DashboardScreen { route, navOptions ->
                navController.navigate(route = route, navOptions = navOptions)
            }
        }

        composable(
            route = AppScreens.ReviewScreen.routeWithArgs,
            arguments = listOf(
                navArgument(name = QUIZ_RESULT_STATE) {
                    type = AssetParamType()
                }
            )
        ) {
            ReviewQuizScreen(
                onPopBackStack = {
                    navController.popBackStack()
                }
            ) { route, navOptions ->
                navController.navigate(route = route, navOptions = navOptions)
            }
        }
    }
}
