package com.thelazybattley.quiz.quizresultshistory.ui

import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavOptions
import com.google.gson.Gson
import com.thelazybattley.common.components.CommonFilterChip
import com.thelazybattley.common.components.CommonTopBar
import com.thelazybattley.common.enums.QuizType
import com.thelazybattley.common.model.AppScreens
import com.thelazybattley.common.ui.theme.QuizAndroidTheme
import com.thelazybattley.domain.mapper.toQuizDetailsState
import com.thelazybattley.domain.model.QuizResult
import com.thelazybattley.quiz.R
import com.thelazybattley.quiz.quizresultshistory.QuizResultsHistoryCallbacks
import com.thelazybattley.quiz.quizresultshistory.QuizResultsHistoryUiState
import com.thelazybattley.quiz.quizresultshistory.QuizResultsHistoryViewModel

@Composable
fun QuizResultsHistoryScreen(
    viewModel: QuizResultsHistoryViewModel = hiltViewModel(),
    onPopBackStack: () -> Unit,
    navigate: (String, NavOptions?) -> Unit
) {
    val uiState by viewModel.state.collectAsState()
    QuizResultsHistoryScreen(
        uiState = uiState, onPopBackStack = onPopBackStack,
        callbacks = viewModel,
        navigate = navigate
    )
}

@Composable
fun QuizResultsHistoryScreen(
    uiState: QuizResultsHistoryUiState,
    onPopBackStack: () -> Unit,
    callbacks: QuizResultsHistoryCallbacks,
    navigate: (String, NavOptions?) -> Unit
) {
    Scaffold(
        topBar = {
            CommonTopBar(
                title = stringResource(id = R.string.quiz_results_history),
                navigationIconRes = com.thelazybattley.common.R.drawable.ic_back_arrow
            ) {
                onPopBackStack()
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(all = 16.dp),
            verticalArrangement = Arrangement.spacedBy(space = 16.dp)
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                contentPadding = paddingValues,
                userScrollEnabled = false
            ) {
                items(
                    items = uiState.categories
                ) { category ->
                    CommonFilterChip(
                        modifier = Modifier,
                        text = category,
                        onClick = {
                            callbacks.onSelectCategory(category = category)
                        },
                        isSelected = category == uiState.selectedCategory
                    )
                }
            }
            HorizontalDivider()
            LazyColumn {
                items(
                    items = uiState.filteredQuizResult
                ) { result ->
                    QuizResultHistoryItem(
                        modifier = Modifier,
                        quizResult = result
                    ) {
                        val json = Uri.encode(Gson().toJson(result.toQuizDetailsState))
                        navigate(AppScreens.ReviewScreen.args(json), null)
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewQuizResultsHistoryScreen() {
    QuizAndroidTheme {
        QuizResultsHistoryScreen(
            uiState = QuizResultsHistoryUiState(
                categories = listOf(
                    "Relationship", "Places", "Dates"
                ),
                filteredQuizResult = listOf(
                    QuizResult(
                        chosenAnswers = listOf(""),
                        answers = listOf(""),
                        type = QuizType.NOLI_ME_TANGERE,
                        category = "Chapter 2",
                        percent = 99,
                        questions = listOf(),
                    ).copy(
                        percent = 50,
                    ),
                    QuizResult(
                        chosenAnswers = listOf(""),
                        answers = listOf(""),
                        type = QuizType.NOLI_ME_TANGERE,
                        category = "Chapter 2",
                        percent = 12,
                        questions = listOf(),
                    ),
                    QuizResult(
                        chosenAnswers = listOf(""),
                        answers = listOf(""),
                        type = QuizType.NOLI_ME_TANGERE,
                        category = "Chapter 2",
                        percent = 80,
                        questions = listOf(),
                    ),
                    QuizResult(
                        chosenAnswers = listOf(""),
                        answers = listOf(""),
                        type = QuizType.NOLI_ME_TANGERE,
                        category = "Chapter 2",
                        percent = 50,
                        questions = listOf(),
                    ),
                )

            ),
            callbacks = QuizResultsHistoryCallbacks.default(),
            onPopBackStack = {}
        ) { _, _ -> }
    }
}
