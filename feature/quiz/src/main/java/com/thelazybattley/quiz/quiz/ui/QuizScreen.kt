package com.thelazybattley.quiz.quiz.ui

import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavOptions
import androidx.navigation.navOptions
import com.google.gson.Gson
import com.thelazybattley.common.components.CommonElevatedButton
import com.thelazybattley.common.components.CommonTopBar
import com.thelazybattley.common.enums.QuestionCategory
import com.thelazybattley.common.model.AppScreens
import com.thelazybattley.common.model.Question
import com.thelazybattley.common.model.QuizDetailsState
import com.thelazybattley.common.ui.theme.QuizAndroidTheme
import com.thelazybattley.common.ui.theme.colors
import com.thelazybattley.common.ui.theme.textStyle
import com.thelazybattley.quiz.R
import com.thelazybattley.quiz.quiz.QuizCallbacks
import com.thelazybattley.quiz.quiz.QuizEvents
import com.thelazybattley.quiz.quiz.QuizUiState
import com.thelazybattley.quiz.quiz.QuizViewModel
import kotlin.time.DurationUnit
import kotlin.time.toDuration

@Composable
fun QuizScreen(
    viewModel: QuizViewModel = hiltViewModel(),
    onPopBackStack: () -> Unit,
    navigate: (String, NavOptions?) -> Unit
) {
    val uiState by viewModel.state.collectAsState()
    val events by viewModel.events.collectAsState(initial = null)

    HandleEvents(event = events, navigate = navigate)
    QuizScreen(
        uiState = uiState,
        callbacks = viewModel,
        onPopBackStack = onPopBackStack
    )
}

@Composable
private fun QuizScreen(
    uiState: QuizUiState,
    callbacks: QuizCallbacks,
    onPopBackStack: () -> Unit
) {

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        containerColor = colors.white50,
        topBar = {
            CommonTopBar(
                titleRes = R.string.quiz,
                navigationIconRes = com.thelazybattley.common.R.drawable.ic_back_arrow,
                actions = {
                    QuizTimer(
                        remainingTime = uiState
                            .remainingTime
                            .toDuration(unit = DurationUnit.SECONDS)
                            .toString()
                    )
                }
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
            QuizQuestionPicker(
                modifier = Modifier.padding(top = 16.dp),
                currentIndex = uiState.currentIndex,
                chosenAnswers = uiState.quizDetailsState.chosenAnswers,
                onQuestionSelected = { index ->
                    callbacks.goToQuestion(index = index)
                },
                progress = uiState.progress
            )
            if (uiState.quizDetailsState.question != null) {
                Text(
                    modifier = Modifier.padding(top = 16.dp),
                    text = uiState.quizDetailsState.question!!.question,
                    style = textStyle.poppins.copy(
                        fontSize = 16.sp,
                        lineHeight = 32.sp
                    )
                )
                Spacer(modifier = Modifier.height(16.dp))
                uiState.quizDetailsState.question!!.choices.forEach { choice ->
                    val isSelected =
                        choice == uiState.quizDetailsState.chosenAnswers[uiState.currentIndex]
                    val (background, style, borderColor) = if (isSelected) {
                        Triple(
                            first = colors.purple30,
                            second = textStyle.poppins.copy(
                                fontSize = 14.sp,
                                color = colors.purple50
                            ),
                            third = colors.purple50
                        )
                    } else {
                        Triple(
                            first = colors.white10,
                            second = textStyle.poppins.copy(
                                fontSize = 14.sp,
                                color = colors.black20
                            ),
                            third = colors.gray20
                        )
                    }

                    QuizChoice(
                        choice = choice,
                        background = background,
                        style = style,
                        borderColor = borderColor
                    ) {
                        callbacks.selectAnswer(chosenAnswer = choice)
                    }
                }
            }
            Spacer(modifier = Modifier.weight(weight = 1f))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 32.dp),
                horizontalArrangement = Arrangement.spacedBy(space = 16.dp),
            ) {
                CommonElevatedButton(
                    modifier = Modifier
                        .weight(1f),
                    textRes = R.string.previous,
                    isEnabled = uiState.currentIndex != 0,
                    colors = ButtonDefaults.elevatedButtonColors(
                        containerColor = colors.white30,
                        contentColor = colors.purple50
                    ),
                    onClick = {
                        callbacks.goToQuestion(index = uiState.currentIndex.dec())
                    }
                )

                val textRes = if (uiState.isComplete) {
                    R.string.submit
                } else {
                    R.string.next
                }
                CommonElevatedButton(
                    modifier = Modifier
                        .weight(1f),
                    textRes = textRes,
                    colors = ButtonDefaults.elevatedButtonColors(
                        containerColor = colors.purple50,
                        contentColor = colors.white20
                    ),
                    onClick = {
                        if (uiState.isComplete) {
                            callbacks.updateSubmitDialog(showDialog = true)
                        } else {
                            callbacks.goToQuestion(index = uiState.currentIndex.inc())
                        }
                    },
                    isEnabled = uiState.currentIndex != uiState.quizDetailsState.questions.size.dec() || uiState.isComplete
                )
            }
            QuizSubmitDialog(
                showDialog = uiState.showSubmitButtonDialog,
                onSubmitButtonClicked = { callbacks.submitQuiz() },
                onContinueQuizClicked = {
                    callbacks.updateSubmitDialog(showDialog = false)
                }
            )
        }
    }
}

@Composable
private fun HandleEvents(event: QuizEvents?, navigate: (String, NavOptions?) -> Unit) {
    LaunchedEffect(key1 = event) {
        when (event) {
            is QuizEvents.FinishedQuizEvent -> {
                val json = Uri.encode(Gson().toJson(event.quizDetailsState))
                navigate(
                    AppScreens.QuizResultScreen.args(json = json), navOptions {
                        popUpTo(AppScreens.DashboardScreen.route)
                    }
                )
            }

            else -> {}
        }
    }
}

@Preview(showBackground = true, device = "id:pixel_2", apiLevel = 28)
@Composable
private fun PreviewQuizScreen() {
    QuizAndroidTheme {
        QuizScreen(
            uiState = QuizUiState(
                quizDetailsState = QuizDetailsState(
                    question = Question(
                        id = 1,
                        question = "Which of the following are true for objects of Python’s set type:",
                        choices = listOf(
                            "Choice 1",
                            "Choice 2",
                            "Choice 3",
                            "Choice 4",
                        ),
                        answer = "Choice 3",
                        category = QuestionCategory.RELATIONSHIP
                    ),
                    chosenAnswers = listOf(
                        null, "",
                        "", null,
                        null, "",
                        null, "",
                        null, "",
                        null, "",
                    ),
                ),
                currentIndex = 3,
                progress = 26f
            ),
            callbacks = object : QuizCallbacks {
                override suspend fun observeTimer() {
                    TODO("Not yet implemented")
                }

                override fun submitQuiz() {
                    TODO("Not yet implemented")
                }

                override fun selectAnswer(chosenAnswer: String) {
                    TODO("Not yet implemented")
                }

                override fun fetchQuestions() {
                    TODO("Not yet implemented")
                }

                override fun goToQuestion(index: Int) {
                    TODO("Not yet implemented")
                }

                override fun updateSubmitDialog(showDialog: Boolean) {
                    TODO("Not yet implemented")
                }
            },
            onPopBackStack = {},
        )
    }
}
