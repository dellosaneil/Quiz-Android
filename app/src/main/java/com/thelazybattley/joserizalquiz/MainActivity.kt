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
//            Timber.d("Test: ")
//            useCase(
//                question ="This is a ELFILI Chapter 2",
//                answer = "2",
//                choices = listOf(
//                    "26",
//                    "36",
//                    "2",
//                    "35",
//                ),
//                category = "Chapter 2",
//                quizType = QuizType.EL_FILI
//            ).also {
//                Timber.d("Test: $it")
//            }
//        }
        setContent {
            QuizAndroidTheme {
                AppNavHost()
            }
        }
    }
}
