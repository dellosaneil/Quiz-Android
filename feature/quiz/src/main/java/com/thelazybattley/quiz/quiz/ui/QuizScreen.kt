package com.thelazybattley.quiz.quiz.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
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

}
