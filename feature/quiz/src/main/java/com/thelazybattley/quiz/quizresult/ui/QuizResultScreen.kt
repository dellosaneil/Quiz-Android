package com.thelazybattley.quiz.quizresult.ui

import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavOptions
import androidx.navigation.navOptions
import com.google.gson.Gson
import com.thelazybattley.common.components.CommonElevatedButton
import com.thelazybattley.common.components.CommonTopBar
import com.thelazybattley.common.ext.toPercentageDisplay
import com.thelazybattley.common.model.AppScreens
import com.thelazybattley.common.presets.KonfettiPreset
import com.thelazybattley.common.ui.theme.QuizAndroidTheme
import com.thelazybattley.common.ui.theme.colors
import com.thelazybattley.common.ui.theme.textStyle
import com.thelazybattley.quiz.R
import com.thelazybattley.quiz.quizresult.QuizResultCallbacks
import com.thelazybattley.quiz.quizresult.QuizResultEvents
import com.thelazybattley.quiz.quizresult.QuizResultUiState
import com.thelazybattley.quiz.quizresult.QuizResultViewModel
import nl.dionsegijn.konfetti.compose.KonfettiView

@Composable
fun QuizResultScreen(
    viewModel: QuizResultViewModel = hiltViewModel(),
    onPopBackStack: () -> Unit,
    navigate: (String, NavOptions?) -> Unit
) {
    val uiState by viewModel.state.collectAsState()
    val events by viewModel.events.collectAsState(initial = null)
    HandleEvents(
        event = events,
        navigate = navigate,
        onPopBackStack = onPopBackStack
    )
    QuizResultScreen(
        uiState = uiState, events = events, callbacks = viewModel,
        onPopBackStack = onPopBackStack
    )
}

@Composable
fun QuizResultScreen(
    uiState: QuizResultUiState,
    events: QuizResultEvents?,
    callbacks: QuizResultCallbacks,
    onPopBackStack: () -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = colors.white50,
        topBar = {
            CommonTopBar(
                titleRes = R.string.show_result,
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
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(
                space = 16.dp,
                alignment = Alignment.CenterVertically
            ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(
                    space = 16.dp,
                    alignment = Alignment.CenterVertically
                ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.quiz_result),
                    style = textStyle.poppins.copy(
                        color = colors.blackOpacity50,
                        fontSize = 16.sp
                    )
                )
                Text(
                    text = stringResource(
                        id = com.thelazybattley.common.R.string.x_percent,
                        uiState.percentage
                    ),
                    style = textStyle.poppins.copy(
                        fontSize = 36.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = colors.purple50
                    )
                )
                Text(
                    text = quizResultText(
                        correctAnswers = uiState.correctAnswers,
                        totalQuestions = uiState.totalQuestions,
                        percentage = uiState.percentage
                    ),
                    style = textStyle.poppins.copy(
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center
                    )
                )
            }

            CommonElevatedButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    callbacks.onReviewQuestionsClicked()
                },
                colors = ButtonDefaults.buttonColors(
                    contentColor = colors.white20,
                    containerColor = colors.purple50
                ),
                textRes = R.string.review_quiz
            )

            CommonElevatedButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    callbacks.onCloseButtonClicked()
                },
                colors = ButtonDefaults.buttonColors(
                    contentColor = colors.purple50,
                    containerColor = colors.white30
                ),
                textRes = R.string.wrap_up_quiz
            )
        }
    }
    if (uiState.percentage >= 90) {
        KonfettiView(
            parties = KonfettiPreset.explode,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
private fun quizResultText(
    correctAnswers: Int,
    totalQuestions: Int,
    percentage: Int
): AnnotatedString {
    val percentageDisplay = percentage.toPercentageDisplay
    return buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                color = percentageDisplay.textColor
            )
        ) {
            append(percentageDisplay.text)
            append("\n")
        }
        withStyle(
            style = SpanStyle(
                color = colors.blackOpacity50
            )
        ) {
            append(
                stringResource(id = R.string.you_have_answered)
            )
        }
        withStyle(
            style = SpanStyle(
                color = colors.purple50
            )
        ) {
            append(" ${correctAnswers}/${totalQuestions} ")
        }
        withStyle(
            style = SpanStyle(
                color = colors.blackOpacity50
            )
        ) {
            append(stringResource(id = R.string.questions_correctly))
        }
    }
}

@Composable
private fun HandleEvents(
    event: QuizResultEvents?,
    onPopBackStack: () -> Unit,
    navigate: (String, NavOptions?) -> Unit
) {
    LaunchedEffect(key1 = event) {
        when (event) {
            is QuizResultEvents.OnCloseButtonClickedEvent -> {
                onPopBackStack()
            }

            is QuizResultEvents.OnReviewQuestionsClicked -> {
                val json = Uri.encode(Gson().toJson(event.quizDetailsState))
                navigate(
                    AppScreens.ReviewScreen.args(json = json), navOptions {
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

@Preview(showBackground = true, device = "id:pixel_2")
@Composable
private fun PreviewQuizResultScreen() {
    QuizAndroidTheme {
        QuizResultScreen(
            uiState = QuizResultUiState(),
            events = null,
            callbacks = object : QuizResultCallbacks {
                override fun onCloseButtonClicked() {
                    TODO("Not yet implemented")
                }

                override fun onReviewQuestionsClicked() {
                    TODO("Not yet implemented")
                }

            },
            onPopBackStack = {

            }
        )
    }
}
