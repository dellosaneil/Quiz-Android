package com.thelazybattley.quiz.quiz.ui

import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavOptions
import androidx.navigation.navOptions
import com.google.gson.Gson
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

@Composable
fun QuizScreen(
    viewModel: QuizViewModel = hiltViewModel(),
    navigate: (String, NavOptions?) -> Unit
) {
    val uiState by viewModel.state.collectAsState()
    val events by viewModel.events.collectAsState(initial = null)
    QuizScreen(
        uiState = uiState,
        events = events,
        callbacks = viewModel,
        navigate = navigate
    )
}

@Composable
private fun QuizScreen(
    uiState: QuizUiState,
    events: QuizEvents?,
    callbacks: QuizCallbacks,
    navigate: (String, NavOptions?) -> Unit
) {

    HandleEvents(event = events, navigate = navigate)

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        containerColor = colors.purple50
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = paddingValues)
        ) {
            QuizProgress(
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .fillMaxWidth(),
                currentQuestionNumber = uiState.currentNumber,
                totalQuestions = uiState.quizDetailsState.questions.size
            )
            Card(
                modifier = Modifier
                    .weight(weight = 1f)
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(
                        bottom = 16.dp,
                        top = 8.dp
                    ),
                colors = CardDefaults.cardColors(
                    containerColor = colors.white50
                )
            ) {
                Column(
                    modifier = Modifier.padding(all = 32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    QuizTimer(
                        timerState = uiState.timerState,
                        modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
                    )
                    if (uiState.quizDetailsState.question != null) {
                        LazyColumn {
                            item {
                                Text(
                                    modifier = Modifier
                                        .padding(top = 16.dp),
                                    text = uiState.quizDetailsState.question.question,
                                    style = textStyle.large.copy(
                                        color = colors.black50,
                                        fontWeight = FontWeight.ExtraBold
                                    )
                                )
                                Spacer(modifier = Modifier.height(16.dp))
                            }

                            items(
                                items = uiState.quizDetailsState.question.choices,
                                key = { it }
                            ) { choice ->
                                QuizChoice(
                                    modifier = Modifier
                                        .padding(vertical = 8.dp),
                                    choice = choice,
                                    isSelected = uiState.currentChosenAnswer == choice
                                ) {
                                    callbacks.selectAnswer(chosenAnswer = choice)
                                }
                            }

                            item {
                                Button(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    onClick = {
                                        if (uiState.isLastQuestion) {
                                            callbacks.checkQuiz()
                                        } else {
                                            callbacks.nextQuestion()
                                        }
                                    },
                                    shape = RoundedCornerShape(size = 8.dp),
                                    enabled = uiState.currentChosenAnswer != null
                                ) {
                                    Text(
                                        text = stringResource(id = R.string.submit),
                                        style = textStyle.medium
                                    )
                                }
                            }
                        }
                    }
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
                        question = "This is a test",
                        choices = listOf(
                            "Choice 1",
                            "Choice 2",
                            "Choice 3",
                            "Choice 4",
                        ),
                        answer = "Choice 3",
                        type = QuestionType.RELATIONSHIP
                    )
                )
            ),
            events = null,
            callbacks = object : QuizCallbacks {
                override fun observeTimer() {
                    TODO("Not yet implemented")
                }

                override fun nextQuestion() {
                    TODO("Not yet implemented")
                }

                override fun selectAnswer(chosenAnswer: String) {
                    TODO("Not yet implemented")
                }

                override fun fetchQuestions() {
                    TODO("Not yet implemented")
                }

                override fun checkQuiz() {
                    TODO("Not yet implemented")
                }

                override fun inputAnswer() {
                    TODO("Not yet implemented")
                }
            },
            navigate = { _, _ ->

            }
        )
    }
}
