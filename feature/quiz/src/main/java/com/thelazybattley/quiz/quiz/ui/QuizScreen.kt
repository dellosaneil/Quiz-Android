package com.thelazybattley.quiz.quiz.ui

import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavOptions
import androidx.navigation.navOptions
import com.google.gson.Gson
import com.thelazybattley.common.components.CommonTopBar
import com.thelazybattley.common.enums.QuestionType
import com.thelazybattley.common.model.AppScreens
import com.thelazybattley.common.ui.theme.QuizAndroidTheme
import com.thelazybattley.common.ui.theme.colors
import com.thelazybattley.common.ui.theme.textStyle
import com.thelazybattley.domain.model.Question
import com.thelazybattley.quiz.R
import com.thelazybattley.quiz.quiz.QuizCallbacks
import com.thelazybattley.quiz.quiz.QuizDetailsState
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
                            .timerState
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
                    text = uiState.quizDetailsState.question.question,
                    style = textStyle.poppins.copy(
                        fontSize = 16.sp,
                        lineHeight = 32.sp
                    )
                )
                Spacer(modifier = Modifier.height(16.dp))
                uiState.quizDetailsState.question.choices.forEach { choice ->
                    QuizChoice(
                        choice = choice,
                        isSelected = choice == uiState.quizDetailsState.chosenAnswers[uiState.currentIndex]
                    ) {
                        callbacks.selectAnswer(chosenAnswer = choice)
                    }
                }
            }
            Spacer(modifier = Modifier.weight(weight = 1f))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(space = 16.dp),
            ) {
                ElevatedButton(
                    modifier = Modifier
                        .weight(1f)
                        .padding(bottom = 32.dp),
                    onClick = {
                        callbacks.goToQuestion(index = uiState.currentIndex.dec())
                    },
                    shape = RoundedCornerShape(size = 8.dp),
                    colors = ButtonDefaults.elevatedButtonColors(
                        containerColor = colors.white30,
                        contentColor = colors.purple50
                    ),
                    contentPadding = PaddingValues(all = 16.dp),
                    enabled = uiState.currentIndex != 0
                ) {
                    Text(
                        text = stringResource(R.string.previous),
                        style = textStyle.poppins.copy(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold
                        ),
                    )
                }

                ElevatedButton(
                    modifier = Modifier
                        .weight(1f)
                        .padding(bottom = 32.dp),
                    onClick = {
                        callbacks.goToQuestion(index = uiState.currentIndex.inc())
                    },
                    shape = RoundedCornerShape(size = 8.dp),
                    colors = ButtonDefaults.elevatedButtonColors(
                        containerColor = colors.purple50,
                        contentColor = colors.white20
                    ),
                    contentPadding = PaddingValues(all = 16.dp),
                    enabled = uiState.currentIndex != uiState.quizDetailsState.questions.size.dec()
                ) {
                    Text(
                        text = stringResource(id = R.string.next),
                        style = textStyle.poppins.copy(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                }
            }
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

            null -> {
                // do nothing
            }
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
                        type = QuestionType.RELATIONSHIP
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
            },
            onPopBackStack = {},
        )
    }
}
