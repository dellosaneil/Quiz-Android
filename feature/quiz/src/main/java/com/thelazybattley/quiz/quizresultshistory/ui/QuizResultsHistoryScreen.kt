package com.thelazybattley.quiz.quizresultshistory.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavOptions
import com.thelazybattley.common.components.CommonTopBar
import com.thelazybattley.quiz.R
import com.thelazybattley.quiz.quizresultshistory.QuizResultsHistoryUiState
import com.thelazybattley.quiz.quizresultshistory.QuizResultsHistoryViewModel

@Composable
fun QuizResultsHistoryScreen(
    viewModel: QuizResultsHistoryViewModel = hiltViewModel(),
    onPopBackStack: () -> Unit,
    navigate: (String, NavOptions?) -> Unit
) {

    val uiState by viewModel.state.collectAsState()

    QuizResultsHistoryScreen(uiState = uiState, onPopBackStack = onPopBackStack)

}

@Composable
fun QuizResultsHistoryScreen(
    uiState: QuizResultsHistoryUiState,
    onPopBackStack: () -> Unit
) {
    Scaffold(
        topBar = {
            CommonTopBar(
                titleRes = R.string.quiz_results_history,
                navigationIconRes = com.thelazybattley.common.R.drawable.ic_back_arrow
            ) {
                onPopBackStack()
            }
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues))
    }

}

@Preview
@Composable
fun PreviewQuizResultsHistoryScreen() {
    QuizResultsHistoryScreen(
        uiState = QuizResultsHistoryUiState()
    ) {

    }
}
