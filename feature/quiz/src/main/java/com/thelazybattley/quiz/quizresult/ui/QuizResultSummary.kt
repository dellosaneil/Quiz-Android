package com.thelazybattley.quiz.quizresult.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.thelazybattley.common.ui.theme.QuizAndroidTheme
import com.thelazybattley.common.ui.theme.colors
import com.thelazybattley.common.ui.theme.textStyle
import com.thelazybattley.quiz.R
import com.thelazybattley.quiz.quizresult.QuizResultUiState

@Composable
fun QuizResultSummary(
    uiState: QuizResultUiState
) {
    ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
        val (correctAnswersRefs, percentageRef, skippedRef, incorrectAnswersRef) = createRefs()
        Details(
            modifier = Modifier
                .constrainAs(ref = correctAnswersRefs) {
                    top.linkTo(anchor = parent.top, margin = 16.dp)
                    start.linkTo(anchor = parent.start, margin = 16.dp)
                },
            label = R.string.correct_answers,
            value = pluralStringResource(R.plurals.x_question, uiState.correctAnswers, uiState.correctAnswers)
        )

        Details(
            modifier = Modifier
                .constrainAs(ref = percentageRef) {
                    top.linkTo(anchor = correctAnswersRefs.top)
                    start.linkTo(anchor = correctAnswersRefs.end, margin = 32.dp)
                },
            label = R.string.percentage,
            value = stringResource(R.string.x_percent, uiState.percentage)
        )

        Details(
            modifier = Modifier.constrainAs(ref = skippedRef) {
                top.linkTo(anchor = correctAnswersRefs.bottom, margin = 8.dp)
                start.linkTo(anchor = correctAnswersRefs.start)
            },
            label = R.string.skipped,
            value =  pluralStringResource(R.plurals.x_question, uiState.skippedQuestions, uiState.skippedQuestions)
        )

        Details(
            modifier = Modifier
                .constrainAs(ref = incorrectAnswersRef) {
                    top.linkTo(anchor = skippedRef.top)
                    start.linkTo(anchor = percentageRef.start)
                },
            label = R.string.incorrect,
            value = pluralStringResource(R.plurals.x_question, uiState.incorrectAnswers, uiState.incorrectAnswers)
        )
    }
}


@Composable
private fun Details(
    modifier: Modifier,
    @StringRes label: Int,
    value: String
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(space = 8.dp)
    ) {
        Text(
            text = stringResource(id = label),
            style = textStyle.medium.copy(
                color = colors.gray30,
                fontWeight = FontWeight.Bold
            )
        )
        Text(
            text = value,
            style = textStyle.medium.copy(
                color = colors.black50,
                fontWeight = FontWeight.Medium
            )
        )
    }
}

@Preview(showBackground = true, device = "id:pixel_2")
@Composable
private fun PreviewQuizResultSummary() {
    QuizAndroidTheme {
        QuizResultSummary(
            uiState = QuizResultUiState()
        )
    }
}
