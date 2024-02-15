package com.thelazybattley.dashboard.dashboard.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thelazybattley.common.ui.theme.QuizAndroidTheme
import com.thelazybattley.common.ui.theme.colors
import com.thelazybattley.common.ui.theme.textStyle

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun DashboardItem(
    modifier: Modifier,
    title: String,
    description: String,
    onClick: () -> Unit
) {

    ElevatedCard(
        modifier = modifier
            .padding(horizontal = 4.dp)
            .width(width = 126.dp),
        onClick = { onClick() },
        colors = CardDefaults.cardColors(
            containerColor = colors.white50
        )
    ) {
        Spacer(modifier = Modifier.height(8.dp))
        Image(
            painter = painterResource(id = com.thelazybattley.common.R.drawable.img_timed_quiz),
            contentDescription = null,
            modifier = Modifier
                .padding(bottom = 16.dp)
                .size(56.dp)
                .align(alignment = Alignment.CenterHorizontally)
        )

        Column(
            modifier = Modifier
                .background(color = colors.gray40.copy(alpha = 0.2f))
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(space = 4.dp)
        ) {
            Divider()
            Text(
                text = title,
                style = textStyle.poppins.copy(
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp,
                    color = colors.black50
                ),
                maxLines = 1,
                modifier = Modifier
                    .basicMarquee()
                    .padding(horizontal = 8.dp)
                    .fillMaxWidth()
            )
            Text(
                text = description,
                style = textStyle.poppins.copy(
                    fontSize = 12.sp,
                    color = colors.black50.copy(alpha = 0.3f)
                ),
                maxLines = 1,
                modifier = Modifier
                    .basicMarquee()
                    .padding(horizontal = 8.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDashboardItem() {
    QuizAndroidTheme {
        DashboardItem(
            modifier = Modifier.padding(all = 16.dp),
            title = "I am a test",
            description = "A Description",
            onClick = {}
        )
    }
}
