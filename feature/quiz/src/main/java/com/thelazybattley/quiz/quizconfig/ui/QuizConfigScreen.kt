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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavOptions
import com.thelazybattley.common.components.CommonElevatedButton
import com.thelazybattley.common.components.CommonTextField
import com.thelazybattley.common.components.CommonTopBar
import com.thelazybattley.common.enums.QuestionCategory
import com.thelazybattley.common.ui.theme.QuizAndroidTheme
import com.thelazybattley.common.ui.theme.colors
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
        events = events,
        callbacks = viewModel,
        onPopBackStack = onPopBackStack,
        navigate = navigate
    )
}

@Composable
private fun QuizConfigScreen(
    uiState: QuizConfigUiState,
    events: QuizConfigEvents?,
    callbacks: QuizConfigCallbacks,
    onPopBackStack: () -> Unit,
    navigate: (String, NavOptions?) -> Unit
) {
    Scaffold(
        topBar = {
            CommonTopBar(
                titleRes = R.string.quiz_configuration,
                navigationIconRes = com.thelazybattley.common.R.drawable.ic_back
            ) {
                onPopBackStack()
            }
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues = paddingValues)) {
            LazyVerticalGrid(
                modifier = Modifier
                    .fillMaxWidth(),
                columns = GridCells.Fixed(count = 3),
                contentPadding = PaddingValues(all = 8.dp)
            ) {
                items(
                    items = uiState.categories,
                    key = { it.category }
                ) { category ->
                    QuizConfigCategoryItem(
                        modifier = Modifier,
                        category = category.category.toString(),
                        isSelected = category.category.name == uiState.selectedCategory?.category?.name
                    ) {
                        callbacks.selectCategory(category = category)
                    }
                }
            }
            if (uiState.selectedCategory != null) {
                val count = if (uiState.count == 0) "" else uiState.count.toString()
                CommonTextField(
                    modifier = Modifier
                        .padding(top = 32.dp)
                        .align(alignment = Alignment.CenterHorizontally),
                    text = count,
                    singleLine = true,
                    placeholderText = "Max Question Count: ${uiState.selectedCategory.count}",
                    onValueChange = {
                        callbacks.updateCount(it)
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    )
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            CommonElevatedButton(
                modifier = Modifier.padding(all = 16.dp),
                textRes = R.string.start_quiz,
                isEnabled = uiState.selectedCategory != null && uiState.count != 0,
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

@Preview(showBackground = true)
@Composable
private fun PreviewQuizConfigScreen() {
    QuizAndroidTheme {
        QuizConfigScreen(
            uiState = QuizConfigUiState(
                categories = listOf(
                    CategoryDetail(count = 1, category = QuestionCategory.PEOPLE),
                    CategoryDetail(count = 1, category = QuestionCategory.RELATIONSHIP),
                    CategoryDetail(count = 1, category = QuestionCategory.ADULTHOOD),
                    CategoryDetail(count = 1, category = QuestionCategory.CHILDHOOD),
                    CategoryDetail(count = 1, category = QuestionCategory.IMPORTANT_PLACES),
                )
            ),
            events = null,
            callbacks = QuizConfigCallbacks.default(),
            onPopBackStack = {},
        ) { _, _ ->

        }
    }
}
