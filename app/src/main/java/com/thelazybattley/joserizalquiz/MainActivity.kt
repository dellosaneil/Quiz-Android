package com.thelazybattley.joserizalquiz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.thelazybattley.common.ui.theme.QuizAndroidTheme
import com.thelazybattley.domain.network.usecase.AddQuestionUseCase
import com.thelazybattley.joserizalquiz.navigation.AppNavHost
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var useCase: AddQuestionUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        lifecycleScope.launch(context = Dispatchers.IO) {
//            useCase(
//                question ="Is this a thing",
//                answer = "it is",
//                choices = listOf(
//                    "it is",
//                    "Am i dreaming"
//                ),
//                category = "Cat",
//                quizType = QuizType.LIFE_OF_RIZAL
//            )
//        }
        setContent {
            QuizAndroidTheme {
                AppNavHost()
            }
        }
    }
}
