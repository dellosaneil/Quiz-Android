package com.thelazybattley.quiz.reviewquiz.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavOptions
import com.thelazybattley.quiz.reviewquiz.ReviewQuizCallbacks
import com.thelazybattley.quiz.reviewquiz.ReviewQuizEvents
import com.thelazybattley.quiz.reviewquiz.ReviewQuizUiState
import com.thelazybattley.quiz.reviewquiz.ReviewQuizViewModel

@Composable
fun ReviewQuizScreen(
    viewModel: ReviewQuizViewModel = hiltViewModel(),
    navigate: (String, NavOptions?) -> Unit
) {

    val uiState by viewModel.state.collectAsState()
    val events by viewModel.events.collectAsState(initial = null)

    ReviewQuizScreen(
        uiState = uiState,
        events = events,
        callbacks = viewModel
    )
}

@Composable
private fun ReviewQuizScreen(
    uiState : ReviewQuizUiState,
    events : ReviewQuizEvents?,
    callbacks: ReviewQuizCallbacks
) {

}
