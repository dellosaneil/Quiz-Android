package com.thelazybattley.quiz.quiz.ui

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thelazybattley.common.ui.theme.QuizAndroidTheme
import com.thelazybattley.common.ui.theme.colors
import com.thelazybattley.common.ui.theme.textStyle
import com.thelazybattley.quiz.R

@Composable
fun QuizProgress(
    modifier: Modifier = Modifier,
    currentQuestionNumber: Int,
    totalQuestions: Int
) {
    val animatedProgress by animateFloatAsState(targetValue = currentQuestionNumber.toFloat() / totalQuestions,
        label = ""
    )
    
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(space = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LinearProgressIndicator(
            modifier = Modifier
                .padding(horizontal = 32.dp),
            progress = animatedProgress,
            color = colors.white50,
            trackColor = colors.purple10,
            strokeCap = StrokeCap.Round
        )
        Text(
            text = stringResource(R.string.question_of, currentQuestionNumber, totalQuestions),
            style = textStyle.medium.copy(
                fontWeight = FontWeight.Bold,
                color = colors.white50
            )
        )
    }
}


@Preview(
    showBackground = true,
    device = "id:pixel_2"
)
@Composable
fun PreviewQuizProgress() {
    QuizAndroidTheme {
        QuizProgress(
            modifier = Modifier
                .background(color = colors.purple50)
                .padding(all = 16.dp),
            currentQuestionNumber = 3,
            totalQuestions = 10
        )
    }
}
