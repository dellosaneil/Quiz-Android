package com.thelazybattley.quiz.quiz.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.thelazybattley.common.ui.theme.QuizAndroidTheme
import com.thelazybattley.common.ui.theme.colors
import com.thelazybattley.common.ui.theme.textStyle
import com.thelazybattley.quiz.quiz.QuizCallbacks
import com.thelazybattley.quiz.quiz.QuizEvents
import com.thelazybattley.quiz.quiz.QuizUiState
import com.thelazybattley.quiz.quiz.QuizViewModel

@Composable
fun QuizScreen(
    viewModel: QuizViewModel = hiltViewModel()
) {
    val uiState by viewModel.state.collectAsState()
    val events by viewModel.events.collectAsState(initial = null)
    QuizScreen(
        uiState = uiState,
        events = events,
        callbacks = viewModel
    )
}

@Composable
private fun QuizScreen(
    uiState: QuizUiState,
    events: QuizEvents?,
    callbacks: QuizCallbacks
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        containerColor = colors.purple50
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = paddingValues)
        ) {
            QuizProgress(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth(),
                currentQuestionNumber = 3,
                totalQuestions = 10
            )

            Card(
                modifier = Modifier
                    .weight(weight = 1f)
                    .padding(horizontal = 16.dp)
                    .padding(
                        bottom = 16.dp,
                        top = 8.dp
                    ),
                colors = CardDefaults.cardColors(
                    containerColor = colors.white50
                )
            ) {
                Column(
                    modifier = Modifier.padding(all = 32.dp)
                ) {
                    QuizTimer(
                        timerState = uiState.timerState,
                        modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
                    )
                    Text(
                        modifier = Modifier
                            .padding(top = 16.dp),
                        text = "This is a test question for testing the question if it is indeed",
                        style = textStyle.large.copy(
                            color = colors.black50,
                            fontWeight = FontWeight.ExtraBold
                        )
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    repeat(4) {
                        QuizChoice(
                            modifier = Modifier
                                .padding(vertical = 8.dp),
                            choice = "$it", isSelected = uiState.selectedIndex == it
                        ) {
                            callbacks.selectAnswer(index = it)
                        }
                    }
                }
            }
        }


    }
}

@Preview(showBackground = true, device = "id:pixel_2", apiLevel = 28)
@Composable
private fun PreviewQuizScreen() {
    QuizAndroidTheme {
        QuizScreen(
            uiState = QuizUiState(),
            events = null,
            callbacks = object : QuizCallbacks {
                override fun observeTimer() {
                    TODO("Not yet implemented")
                }

                override fun nextQuestion() {
                    TODO("Not yet implemented")
                }

                override fun selectAnswer(index: Int) {
                    TODO("Not yet implemented")
                }
            }
        )
    }
}
