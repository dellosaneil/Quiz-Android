package com.thelazybattley.quiz.quizresult.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.thelazybattley.common.ui.theme.QuizAndroidTheme
import com.thelazybattley.common.ui.theme.colors
import com.thelazybattley.common.ui.theme.textStyle
import com.thelazybattley.quiz.R
import com.thelazybattley.quiz.quizresult.QuizResultCallbacks
import com.thelazybattley.quiz.quizresult.QuizResultEvents
import com.thelazybattley.quiz.quizresult.QuizResultUiState
import com.thelazybattley.quiz.quizresult.QuizResultViewModel

@Composable
fun QuizResultScreen(
    viewModel: QuizResultViewModel = hiltViewModel()
) {
    val uiState by viewModel.state.collectAsState()
    val events by viewModel.events.collectAsState(initial = null)
    QuizResultScreen(uiState = uiState, events = events, callbacks = viewModel)
}

@Composable
fun QuizResultScreen(
    uiState: QuizResultUiState,
    events: QuizResultEvents?,
    callbacks: QuizResultCallbacks
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = colors.white50
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues = paddingValues)
                .padding(all = 16.dp),
            verticalArrangement = Arrangement.spacedBy(space = 16.dp),
        ) {
            LazyColumn(modifier = Modifier.weight(1f)) {
                item {
                    QuizResultCard(
                        percentage = uiState.percentage
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    QuizResultSummary(uiState = uiState)
                }
            }
            QuizResultButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {

            }
        }
    }
}

@Composable
private fun QuizResultButton(
    modifier: Modifier,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        shape = RoundedCornerShape(size = 8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = colors.purple50
        )
    ) {
        Text(
            text = stringResource(R.string.close),
            style = textStyle.medium.copy(
                color = colors.white50,
                fontWeight = FontWeight.Medium
            ),
        )
    }
}


@Preview(showBackground = true, device = "id:pixel_2")
@Composable
private fun PreviewQuizResultScreen() {
    QuizAndroidTheme {
        QuizResultScreen(
            uiState = QuizResultUiState(),
            events = null,
            callbacks = object : QuizResultCallbacks {

            }
        )
    }
}
