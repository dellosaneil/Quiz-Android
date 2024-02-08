package com.thelazybattley.quiz.quiz.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thelazybattley.common.ui.theme.QuizAndroidTheme
import com.thelazybattley.common.ui.theme.colors
import com.thelazybattley.common.ui.theme.textStyle

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun QuizChoice(
    modifier: Modifier = Modifier,
    choice: String,
    isSelected: Boolean,
    onSelected: () -> Unit
) {

    val (background, style) = if (isSelected) {
        colors.purple20 to textStyle.medium.copy(
            fontWeight = FontWeight.Bold
        )
    } else {
        colors.white50 to textStyle.medium
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .border(
                border = BorderStroke(
                    width = 2.dp,
                    color = colors.purple20
                ),
                shape = RoundedCornerShape(size = 8.dp)
            )
            .background(
                color = background,
                shape = RoundedCornerShape(size = 8.dp)
            )
            .clip(shape = RoundedCornerShape(size = 8.dp))
            .clickable {
                onSelected()
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = isSelected,
            onClick = {
                onSelected()
            },
            colors = RadioButtonDefaults.colors(
                selectedColor = colors.purple50,
                unselectedColor = colors.purple10
            )
        )
        Text(
            text = choice,
            modifier = Modifier
                .padding(top = 16.dp, bottom = 16.dp, end = 16.dp)
                .basicMarquee(),
            maxLines = 1,
            style = style
        )
    }
}

@Preview(showBackground = true, device = "id:pixel_2")
@Composable
private fun PreviewQuizChoice() {
    QuizAndroidTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(space = 16.dp),
            modifier = Modifier.padding(16.dp)
        ) {
            QuizChoice(choice = "Test", isSelected = false) {

            }


            QuizChoice(choice = "Selected", isSelected = true) {

            }
        }
    }
}
