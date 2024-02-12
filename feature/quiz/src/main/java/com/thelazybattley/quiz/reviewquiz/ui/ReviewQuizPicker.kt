package com.thelazybattley.quiz.reviewquiz.ui

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thelazybattley.common.components.CommonLinearProgressIndicator
import com.thelazybattley.common.ui.theme.QuizAndroidTheme
import com.thelazybattley.common.ui.theme.colors
import com.thelazybattley.quiz.quiz.ui.QuestionNumber

@Composable
fun ReviewQuizPicker(
    modifier: Modifier = Modifier,
    currentIndex: Int,
    isAnswersCorrect: List<Boolean>,
    progress: Float,
    onQuestionSelected: (Int) -> Unit
) {
    val animatedProgress by animateFloatAsState(targetValue = progress, label = "")

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(space = 16.dp)
    ) {
        LazyRow {
            itemsIndexed(items = isAnswersCorrect) { index, isCorrect ->
                val isFocused = index == currentIndex
                val color = when {
                    isCorrect -> colors.green30
                    else -> colors.red30
                }
                val background = if (isFocused) {
                    color.copy(alpha = 0.3f)
                } else {
                    colors.white50
                }

                QuestionNumber(
                    modifier = Modifier
                        .padding(horizontal = 8.dp),
                    index = index,
                    onQuestionSelected = onQuestionSelected,
                    background = background,
                    textColor = color,
                    borderColor = color
                )
            }
        }

        CommonLinearProgressIndicator(progress = animatedProgress)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewReviewQuizPicker() {
    QuizAndroidTheme {
        ReviewQuizPicker(
            modifier = Modifier.padding(all = 16.dp),
            isAnswersCorrect = listOf(
                true, false,
                false, true
            ),
            currentIndex = 1,
            progress = 30f,
            onQuestionSelected = {

            }
        )
    }
}
