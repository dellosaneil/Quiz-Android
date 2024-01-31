package com.thelazybattley.quizandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.lifecycleScope
import com.thelazybattley.domain.network.usecase.AddQuestionUseCase
import com.thelazybattley.quizandroid.ui.theme.QuizAndroidTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var useCase: AddQuestionUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            println(
                "Test: ${
                    useCase(
                        question = "Testing?",
                        answer = "yes",
                        choices = listOf("1", "2", "3", "4")
                    )
                }"
            )
        }
        setContent {
            QuizAndroidTheme {

            }
        }
    }
}
