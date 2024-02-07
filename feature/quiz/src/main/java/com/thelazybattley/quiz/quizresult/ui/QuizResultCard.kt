package com.thelazybattley.quiz.quizresult.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thelazybattley.common.ui.theme.QuizAndroidTheme
import com.thelazybattley.common.ui.theme.colors
import com.thelazybattley.common.ui.theme.textStyle
import com.thelazybattley.quiz.R

@Composable
fun QuizResultCard(
    modifier: Modifier = Modifier,
    percentage: Int
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = colors.purple50,
        )
    ) {
        Column(
            modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(space = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = com.thelazybattley.common.R.drawable.img_trophy),
                contentDescription = "",
                modifier = Modifier.size(size = 126.dp)
            )
            Text(
                text = stringResource(R.string.congratulations_you_got, percentage),
                style = textStyle.medium.copy(
                    color = colors.white50,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center
                )
            )
            Button(
                onClick = {

                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = colors.purple10
                ),
                shape = RoundedCornerShape(size = 8.dp)
            ) {
                Text(
                    text = "Check Correct Answers",
                    style = textStyle.medium.copy(
                        color = colors.white50,
                        fontWeight = FontWeight.Medium
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true, device = "id:pixel_2")
@Composable
private fun PreviewQuizResultCard() {
    QuizAndroidTheme {
        QuizResultCard(percentage = 87)
    }

}
