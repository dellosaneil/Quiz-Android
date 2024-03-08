package com.thelazybattley.quiz.quiz.ui

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thelazybattley.common.components.CommonLinearProgressIndicator
import com.thelazybattley.common.ui.theme.QuizAndroidTheme
import com.thelazybattley.common.ui.theme.colors
import com.thelazybattley.common.ui.theme.textStyle

@Composable
fun QuizQuestionPicker(
    modifier: Modifier = Modifier,
    currentIndex: Int,
    chosenAnswers: List<String?>,
    progress: Float,
    scrollState: LazyListState,
    onQuestionSelected: (Int) -> Unit
) {
    val animatedProgress by animateFloatAsState(targetValue = progress, label = "")

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(space = 16.dp)
    ) {
        LazyRow(state = scrollState) {
            itemsIndexed(items = chosenAnswers) { index, answer ->
                val isFocused = currentIndex == index
                val hasAnswer = answer != null

                val background = when {
                    isFocused -> colors.black20
                    hasAnswer -> colors.purple50
                    else -> colors.white50
                }
                val textColor = when {
                    isFocused || hasAnswer -> colors.white10
                    else -> colors.gray20
                }
                val borderColor = when {
                    isFocused || hasAnswer -> colors.transparent
                    else -> colors.gray20
                }

                QuestionNumber(
                    modifier = Modifier
                        .padding(horizontal = 8.dp),
                    index = index,
                    onQuestionSelected = onQuestionSelected,
                    background = background,
                    textColor = textColor,
                    borderColor = borderColor
                )
            }
        }

        CommonLinearProgressIndicator(progress = animatedProgress)

    }
}

@Composable
fun QuestionNumber(
    modifier: Modifier,
    index: Int,
    onQuestionSelected: (Int) -> Unit,
    background: Color,
    textColor: Color,
    borderColor: Color
) {

    Box(
        modifier = modifier
            .size(size = 36.dp)
            .clip(CircleShape)
            .background(
                color = background
            )
            .border(
                border = BorderStroke(
                    width = 1.dp,
                    color = borderColor
                ),
                shape = CircleShape
            )
            .clickable { onQuestionSelected(index) },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = index.inc().toString(),
            style = textStyle.poppins.copy(
                fontSize = 14.sp,
                color = textColor
            )
        )
    }
}

@Preview(showBackground = true, device = "id:pixel_2")
@Composable
fun PreviewQuizQuestionPicker() {
    QuizAndroidTheme {
        QuizQuestionPicker(
            modifier = Modifier.padding(all = 8.dp),
            currentIndex = 2,
            chosenAnswers = listOf(
                "", "", null, null, ""
            ),
            progress = 50f,
            scrollState = rememberLazyListState()
        ) {

        }
    }
}
