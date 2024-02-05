package com.thelazybattley.quiz.quiz.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.thelazybattley.common.ui.theme.colors
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
        containerColor = colors.white50
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(all = 16.dp)
                .padding(paddingValues = paddingValues)
        ) {
            QuizTimer(
                timerState = uiState.timerState,
                modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
            )
        }
    }
}

@Preview(showBackground = true, device = "id:pixel_2")
@Composable
private fun PreviewQuizScreen() {
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

        }
    )
}
