package com.thelazybattley.feedback.createquestion.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.thelazybattley.common.ui.theme.QuizAndroidTheme
import com.thelazybattley.feedback.createquestion.CreateQuestionCallbacks
import com.thelazybattley.feedback.createquestion.CreateQuestionUiState
import com.thelazybattley.feedback.createquestion.CreateQuestionViewModel

@Composable
fun CreateQuestionScreen(
    onPopBackStack: () -> Unit
) {
    val viewModel = hiltViewModel<CreateQuestionViewModel>()
    val uiState by viewModel.state.collectAsState()

    CreateQuestionScreen(
        uiState = uiState,
        callbacks = viewModel
    )
}

@Composable
private fun CreateQuestionScreen(
    uiState: CreateQuestionUiState,
    callbacks: CreateQuestionCallbacks
) {

}

@Preview
@Composable
private fun PreviewCreateQuestionScreen() {
    QuizAndroidTheme {
        CreateQuestionScreen {

        }
    }
}
