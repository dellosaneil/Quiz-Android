package com.thelazybattley.common.model

sealed class AppScreens(val route: String, val routeWithArgs: String = route) {

    companion object {
        const val QUIZ_RESULT_STATE = "quiz-result"
    }

    data object QuizScreen : AppScreens(route = "quiz_screen")

    data object QuizResultScreen : AppScreens(
        route = "quiz_result_screen",
        routeWithArgs = "quiz_result_screen/{$QUIZ_RESULT_STATE}"
    ) {
        fun args(json: String): String {
            return QuizResultScreen.route + "/{$json}"
        }
    }


}
