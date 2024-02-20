package com.thelazybattley.common.model

sealed class AppScreens(val route: String, val routeWithArgs: String = route) {

    companion object {
        const val QUIZ_RESULT_STATE = "quiz-result"
        const val QUIZ_CATEGORY = "quiz-category"
        const val QUESTIONS_COUNT = "questions-count"
    }

    data object QuizScreen : AppScreens(
        route = "quiz_screen",
        routeWithArgs = "quiz_screen/{$QUESTIONS_COUNT}?$QUIZ_CATEGORY={$QUIZ_CATEGORY}"
    ) {
        fun args(category: String?, count: Int): String {
            return QuizScreen.route + "/$count?$QUIZ_CATEGORY=$category"
        }
    }

    data object QuizResultScreen : AppScreens(
        route = "quiz_result_screen",
        routeWithArgs = "quiz_result_screen/{$QUIZ_RESULT_STATE}"
    ) {
        fun args(json: String): String {
            return QuizResultScreen.route + "/$json"
        }
    }

    data object DashboardScreen : AppScreens(route = "dashboard_screen")

    data object ReviewScreen : AppScreens(
        route = "review_screen",
        routeWithArgs = "review_screen/{$QUIZ_RESULT_STATE}"
    ) {
        fun args(json: String): String {
            return ReviewScreen.route + "/$json"
        }
    }

    data object QuizConfigScreen : AppScreens(
        route = "quiz_config_screen",
        routeWithArgs = "quiz_config_screen?$QUIZ_CATEGORY={$QUIZ_CATEGORY}"
    ) {
        fun args(category: String?) : String {
            return QuizConfigScreen.route + "?$QUIZ_CATEGORY=$category"
        }
    }

    data object QuizResultsHistoryScreen : AppScreens(
        route = "quiz_results_history_screen"
    )
}
