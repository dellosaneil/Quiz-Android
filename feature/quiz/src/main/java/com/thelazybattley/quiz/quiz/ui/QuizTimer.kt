package com.thelazybattley.quiz.quiz.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thelazybattley.common.ui.theme.QuizAndroidTheme
import com.thelazybattley.common.ui.theme.colors
import com.thelazybattley.common.ui.theme.textStyle

@Composable
fun QuizTimer(
    modifier: Modifier = Modifier,
    remainingTime: String
) {
    Row(
        modifier = modifier
            .clip(shape = RoundedCornerShape(size = 10.dp))
            .background(color = colors.black20)
            .padding(all = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(space = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier)
        Icon(
            painter = painterResource(id = com.thelazybattley.common.R.drawable.ic_alarm),
            contentDescription = "",
            modifier = Modifier.size(24.dp),
            tint = colors.white50
        )
        Text(
            text = remainingTime,
            style = textStyle.poppins.copy(
                color = colors.white50,
                fontSize = 16.sp
            )
        )
        Spacer(modifier = Modifier)
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewQuizTimer() {
    QuizAndroidTheme {
        QuizTimer(
            remainingTime = "01:45",
            modifier = Modifier.padding(16.dp)
        )
    }
}
