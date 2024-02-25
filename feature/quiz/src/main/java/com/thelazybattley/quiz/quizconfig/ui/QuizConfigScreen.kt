package com.thelazybattley.quiz.quizconfig.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavOptions
import androidx.navigation.navOptions
import com.thelazybattley.common.components.CommonElevatedButton
import com.thelazybattley.common.components.CommonFilterChip
import com.thelazybattley.common.components.CommonTextField
import com.thelazybattley.common.components.CommonTopBar
import com.thelazybattley.common.model.AppScreens
import com.thelazybattley.common.ui.theme.QuizAndroidTheme
import com.thelazybattley.common.ui.theme.colors
import com.thelazybattley.common.ui.theme.textStyle
import com.thelazybattley.domain.model.CategoryDetail
import com.thelazybattley.quiz.R
import com.thelazybattley.quiz.quizconfig.QuizConfigCallbacks
import com.thelazybattley.quiz.quizconfig.QuizConfigEvents
import com.thelazybattley.quiz.quizconfig.QuizConfigUiState
import com.thelazybattley.quiz.quizconfig.QuizConfigViewModel

@Composable
fun QuizConfigScreen(
    viewModel: QuizConfigViewModel = hiltViewModel(),
    onPopBackStack: () -> Unit,
    navigate: (String, NavOptions?) -> Unit
) {
    val uiState by viewModel.state.collectAsState()
    val events by viewModel.events.collectAsState(initial = null)

    QuizConfigScreen(
        uiState = uiState,
        callbacks = viewModel,
        onPopBackStack = onPopBackStack,
    )
    HandleEvents(event = events, navigate = navigate)
}

@Composable
private fun QuizConfigScreen(
    uiState: QuizConfigUiState,
    callbacks: QuizConfigCallbacks,
    onPopBackStack: () -> Unit,
) {
    Scaffold(
        topBar = {
            CommonTopBar(
                titleRes = R.string.quiz_configuration,
                navigationIconRes = com.thelazybattley.common.R.drawable.ic_back_arrow
            ) {
                onPopBackStack()
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues = paddingValues)
                .padding(all = 16.dp)
        ) {
            Text(
                text = stringResource(R.string.choose_quiz_category),
                style = textStyle.poppins.copy(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
            )

            LazyVerticalGrid(
                modifier = Modifier
                    .fillMaxWidth(),
                columns = GridCells.Fixed(count = 2),
                contentPadding = PaddingValues(all = 8.dp)
            ) {
                items(
                    items = uiState.categories,
                    key = { it.category }
                ) { category ->
                    CommonFilterChip(
                        modifier = Modifier,
                        text = category.category,
                        isSelected = category.category == uiState.selectedCategory?.category
                    ) {
                        callbacks.selectCategory(category = category)
                    }
                }
            }
            Text(
                text = stringResource(R.string.input_question_count),
                style = textStyle.poppins.copy(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
            )

            val count = if (uiState.count == 0) "" else uiState.count.toString()
            CommonTextField(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .align(alignment = Alignment.CenterHorizontally),
                text = count,
                singleLine = true,
                placeholderText = stringResource(
                    R.string.max_question_count,
                    uiState.selectedCategory?.count ?: uiState.totalQuestions
                ),
                onValueChange = { newValue ->
                    callbacks.updateCount(newValue)
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                )
            )
            Spacer(modifier = Modifier.weight(1f))
            CommonElevatedButton(
                modifier = Modifier,
                textRes = R.string.start_quiz,
                isEnabled = uiState.count != 0,
                colors = ButtonDefaults.elevatedButtonColors(
                    containerColor = colors.purple50,
                    contentColor = colors.white20
                )
            ) {
                callbacks.startQuiz()
            }
        }
    }
}

@Composable
private fun HandleEvents(event: QuizConfigEvents?, navigate: (String, NavOptions?) -> Unit) {
    LaunchedEffect(key1 = event) {
        when (event) {
            is QuizConfigEvents.StartQuiz -> {
                navigate(
                    AppScreens.QuizScreen.args(
                        category = event.category,
                        count = event.count,
                        quizType = event.quizType.type
                    ),
                    navOptions {

                    }
                )
            }

            else -> {
                // do nothing
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewQuizConfigScreen() {
    QuizAndroidTheme {
        QuizConfigScreen(
            uiState = QuizConfigUiState(
                categories = listOf(
                    CategoryDetail(count = 1, category = "People"),
                    CategoryDetail(count = 1, category = "Relationship"),
                    CategoryDetail(count = 1, category = "Adulthood"),
                    CategoryDetail(count = 1, category = "Dates"),
                    CategoryDetail(count = 1, category = "Places"),

                )
            ),
            callbacks = QuizConfigCallbacks.default(),
            onPopBackStack = {},
        )
    }
}
