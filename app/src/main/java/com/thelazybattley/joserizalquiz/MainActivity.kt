package com.thelazybattley.joserizalquiz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.lifecycleScope
import com.thelazybattley.common.ui.theme.QuizAndroidTheme
import com.thelazybattley.domain.network.usecase.AddQuestionUseCase
import com.thelazybattley.joserizalquiz.navigation.AppNavHost
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var useCase: AddQuestionUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch(context = Dispatchers.IO) {
//            useCase(
//                question ="How old was Rizal when he died?\n",
//                answer = "35",
//                choices = listOf(
//                    "26",
//                    "36",
//                    "45",
//                    "35",
//                ),
//                category = "Life",
//                quizType = QuizType.LIFE_OF_RIZAL
//            )
        }
        setContent {
            QuizAndroidTheme {
                AppNavHost()
            }
        }
    }
}
