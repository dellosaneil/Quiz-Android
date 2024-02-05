package com.thelazybattley.quiz.quiz.ui

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thelazybattley.common.ui.theme.QuizAndroidTheme
import com.thelazybattley.common.ui.theme.colors
import com.thelazybattley.common.ui.theme.textStyle
import com.thelazybattley.quiz.quiz.TimerState

@Composable
fun QuizTimer(
    modifier: Modifier = Modifier,
    timerState: TimerState,
) {
    val color = colors.pink30
    val degrees by animateFloatAsState(targetValue = (timerState.remainingTime / timerState.totalTime) * -360f,
        label = ""
    )
    Box(
        modifier = modifier
            .clip(shape = CircleShape)
            .size(size = 56.dp)
            .background(color = colors.pink10)
            .drawWithContent {
                drawArc(
                    color = color,
                    startAngle = -90f,
                    sweepAngle = (degrees),
                    useCenter = true
                )
                drawContent()
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "${timerState.remainingTime.toInt()}",
            style = textStyle.large.copy(
                color = colors.white50,
                fontWeight = FontWeight.Bold
            )
        )
    }
}

@Preview(showBackground = true, device = "id:pixel_2")
@Composable
private fun PreviewQuizTimer() {
    QuizAndroidTheme {
        QuizTimer(
            timerState = TimerState()
        )
    }
}
