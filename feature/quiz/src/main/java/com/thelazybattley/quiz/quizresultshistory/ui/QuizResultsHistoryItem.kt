package com.thelazybattley.quiz.quizresultshistory.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.thelazybattley.common.enums.QuestionCategory
import com.thelazybattley.common.ext.toPercentageDisplay
import com.thelazybattley.common.ui.theme.QuizAndroidTheme
import com.thelazybattley.common.ui.theme.colors
import com.thelazybattley.common.ui.theme.textStyle
import com.thelazybattley.domain.model.QuizResult
import com.thelazybattley.quiz.R

@Composable
fun QuizResultHistoryItem(
    modifier: Modifier = Modifier,
    quizResult: QuizResult,
    onClick: () -> Unit
) {
    val percentageDisplay = quizResult.percent.toPercentageDisplay

    Card(
        modifier = modifier,
        onClick = { onClick() },
        shape = RoundedCornerShape(size = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = colors.white50
        ),
        border = BorderStroke(width = 1.dp, color = percentageDisplay.textColor)
    ) {
        Row(
            modifier = Modifier
                .padding(all = 16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(space = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = scoreText(score = quizResult.percent),
                style = textStyle.poppins.copy(
                    color = percentageDisplay.textColor,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center
                )
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(space = 4.dp),
            ) {
                Text(
                    text = categoryText(category = quizResult.category.toString()),
                    style = textStyle.poppins.copy(
                        color = colors.black50,
                        fontSize = 12.sp
                    )
                )

                Text(
                    text = questionCountText(count = quizResult.questions.size),
                    style = textStyle.poppins.copy(
                        color = colors.black50,
                        fontSize = 12.sp
                    )
                )
            }
        }
    }
}

@Composable
private fun questionCountText(count: Int): AnnotatedString {
    return buildAnnotatedString {
        withStyle(style = SpanStyle()) {
            append(stringResource(R.string.questions_count))
        }
        withStyle(
            style = SpanStyle(
                fontWeight = FontWeight.SemiBold
            )
        ) {
            append(" $count")
        }
    }
}

@Composable
private fun scoreText(score: Int): AnnotatedString {
    return buildAnnotatedString {
        withStyle(style = SpanStyle()) {
            append(stringResource(R.string.your_score))
        }
        withStyle(
            style = SpanStyle(
                fontWeight = FontWeight.SemiBold
            )
        ) {
            append("\n")
            append(score.toString())
        }
    }
}

@Composable
private fun categoryText(category: String): AnnotatedString {
    return buildAnnotatedString {
        withStyle(style = SpanStyle()) {
            append(stringResource(R.string.category))
        }
        withStyle(
            style = SpanStyle(
                fontWeight = FontWeight.SemiBold
            )
        ) {
            append(" $category")
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun PreviewQuizResultHistoryItem() {
    QuizAndroidTheme {
        QuizResultHistoryItem(
            modifier = Modifier.padding(all = 16.dp),
            quizResult = QuizResult(
                percent = 60,
                category = QuestionCategory.IMPORTANT_PLACES
            )
        ) {

        }
    }
}
