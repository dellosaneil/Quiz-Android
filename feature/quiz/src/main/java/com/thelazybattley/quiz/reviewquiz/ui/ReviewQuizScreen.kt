package com.thelazybattley.quiz.reviewquiz.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavOptions
import com.thelazybattley.common.components.CommonTopBar
import com.thelazybattley.common.enums.QuestionType
import com.thelazybattley.common.ui.theme.QuizAndroidTheme
import com.thelazybattley.common.ui.theme.colors
import com.thelazybattley.common.ui.theme.textStyle
import com.thelazybattley.domain.model.Question
import com.thelazybattley.quiz.R
import com.thelazybattley.quiz.quiz.QuizDetailsState
import com.thelazybattley.quiz.quiz.ui.QuizChoice
import com.thelazybattley.quiz.reviewquiz.ReviewQuizCallbacks
import com.thelazybattley.quiz.reviewquiz.ReviewQuizEvents
import com.thelazybattley.quiz.reviewquiz.ReviewQuizUiState
import com.thelazybattley.quiz.reviewquiz.ReviewQuizViewModel

@Composable
fun ReviewQuizScreen(
    viewModel: ReviewQuizViewModel = hiltViewModel(),
    onPopBackStack: () -> Unit,
    navigate: (String, NavOptions?) -> Unit,
) {

    val uiState by viewModel.state.collectAsState()
    val events by viewModel.events.collectAsState(initial = null)

    ReviewQuizScreen(
        uiState = uiState,
        events = events,
        callbacks = viewModel,
        onPopBackStack = onPopBackStack
    )
}

@Composable
private fun ReviewQuizScreen(
    uiState: ReviewQuizUiState,
    events: ReviewQuizEvents?,
    callbacks: ReviewQuizCallbacks,
    onPopBackStack: () -> Unit
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        containerColor = colors.white50,
        topBar = {
            CommonTopBar(
                titleRes = R.string.review_quiz,
                navigationIconRes = com.thelazybattley.common.R.drawable.ic_back_arrow
            ) {
                onPopBackStack()
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues = paddingValues)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(space = 8.dp)
        ) {
            ReviewQuizPicker(
                modifier = Modifier.padding(top = 16.dp),
                currentIndex = uiState.currentIndex,
                isAnswersCorrect = uiState.isAnswersCorrect,
                progress = uiState.progress,
                onQuestionSelected = { index ->
                    callbacks.jumpToQuestion(index = index)
                }
            )
            if (uiState.quizDetailsState?.question != null) {
                Text(
                    modifier = Modifier.padding(top = 16.dp),
                    text = uiState.quizDetailsState.question.question,
                    style = textStyle.poppins.copy(
                        fontSize = 16.sp,
                        lineHeight = 32.sp
                    )
                )
                Spacer(modifier = Modifier.height(16.dp))
                val index = uiState.currentIndex
                val correctAnswer = uiState.quizDetailsState.answers[index]
                val selectedAnswer = uiState.quizDetailsState.chosenAnswers[index]

                uiState.quizDetailsState.question.choices.forEach { choice ->
                    val isCorrect = correctAnswer == choice
                    val isChosen = selectedAnswer == choice
                    val (background, style, borderColor) = when {
                        isCorrect -> {
                            Triple(
                                first = colors.green30.copy(
                                    alpha = 0.3f
                                ),
                                second = textStyle.poppins.copy(
                                    fontSize = 14.sp,
                                    color = colors.green50
                                ),
                                third = colors.green50
                            )
                        }

                        isChosen -> Triple(
                            first = colors.red30.copy(
                                alpha = 0.3f
                            ),
                            second = textStyle.poppins.copy(
                                fontSize = 14.sp,
                                color = colors.red40
                            ),
                            third = colors.red40
                        )

                        else -> {
                            Triple(
                                first = colors.white10,
                                second = textStyle.poppins.copy(
                                    fontSize = 14.sp,
                                    color = colors.black20
                                ),
                                third = colors.gray20
                            )
                        }
                    }

                    QuizChoice(
                        choice = choice,
                        background = background,
                        style = style,
                        borderColor = borderColor,
                        onClickEnabled = false
                    ) {

                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewReviewQuizScreen() {
    QuizAndroidTheme {
        ReviewQuizScreen(
            uiState = ReviewQuizUiState().copy(
                isAnswersCorrect = listOf(
                    true,
                    false,
                    false,
                    true
                ),
                quizDetailsState = QuizDetailsState(
                    question = Question(
                        id = 1,
                        question = "This is a question",
                        choices = listOf(
                            "1", "2", "3", "4"
                        ),
                        answer = "3",
                        type = QuestionType.RELATIONSHIP
                    )
                )
            ),
            events = null,
            callbacks = object : ReviewQuizCallbacks {
                override fun jumpToQuestion(index: Int) {
                    TODO("Not yet implemented")
                }

            },
            onPopBackStack = {

            }
        )
    }
}
