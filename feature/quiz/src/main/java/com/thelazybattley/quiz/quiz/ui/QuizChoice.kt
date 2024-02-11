package com.thelazybattley.quiz.quiz.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thelazybattley.common.ui.theme.QuizAndroidTheme
import com.thelazybattley.common.ui.theme.colors
import com.thelazybattley.common.ui.theme.textStyle

@Composable
fun QuizChoice(
    modifier: Modifier = Modifier,
    choice: String,
    background: Color,
    style: TextStyle,
    borderColor: Color,
    onClickEnabled: Boolean = true,
    onSelected: () -> Unit
) {

    Box(
        modifier = modifier
            .fillMaxWidth()
            .border(
                border = BorderStroke(
                    width = 1.dp,
                    color = borderColor
                ),
                shape = RoundedCornerShape(size = 8.dp)
            )
            .background(
                color = background,
                shape = RoundedCornerShape(size = 8.dp)
            )
            .clip(shape = RoundedCornerShape(size = 8.dp))
            .clickable(enabled = onClickEnabled) {
                onSelected()
            }
    ) {
        Text(
            text = choice,
            modifier = Modifier
                .padding(all = 16.dp),
            maxLines = 3,
            style = style
        )
    }
}

@Preview(showBackground = true, device = "id:pixel_2")
@Composable
private fun PreviewQuizChoice() {
    QuizAndroidTheme {
        val (background, style, borderColor) =
            Triple(
                first = colors.purple30,
                second = textStyle.poppins.copy(
                    fontSize = 14.sp,
                    color = colors.purple50
                ),
                third = colors.purple50
            )
        Column(
            verticalArrangement = Arrangement.spacedBy(space = 16.dp),
            modifier = Modifier.padding(16.dp)
        ) {
            QuizChoice(
                choice = "Test", background = background,
                style = style,
                borderColor = borderColor
            ) {

            }


            QuizChoice(
                choice = "Selected", background = background,
                style = style,
                borderColor = borderColor
            ) {

            }

            QuizChoice(
                choice = "A Long text to test a test that is a testing a testakjsdbnlak sd adsa sd   ",
                background = background,
                style = style,
                borderColor = borderColor
            ) {

            }


            QuizChoice(
                choice = "Selected", background = background,
                style = style,
                borderColor = borderColor
            ) {

            }
        }
    }
}
