package com.thelazybattley.dashboard.dashboard.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thelazybattley.common.R
import com.thelazybattley.common.ext.toPercentageDisplay
import com.thelazybattley.common.ui.theme.QuizAndroidTheme
import com.thelazybattley.common.ui.theme.colors
import com.thelazybattley.common.ui.theme.textStyle

@Composable
fun DashboardReviewItemContent(
    modifier: Modifier,
    percentage: Int
) {
    val percentageDisplay = percentage.toPercentageDisplay

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(space = 4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(
                id = R.string.x_percent,
                percentage
            ),
            style = textStyle.poppins.copy(
                color = percentageDisplay.textColor,
                fontWeight = FontWeight.SemiBold,
                fontSize = 26.sp,
                textAlign = TextAlign.Center
            ),
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}

@Preview
@Composable
fun PreviewDashboardReviewItem() {
    QuizAndroidTheme {
        Column {
            DashboardReviewItemContent(
                modifier = Modifier
                    .padding(all = 16.dp)
                    .background(color = colors.white50),
                percentage = 91,
            )

            DashboardReviewItemContent(
                modifier = Modifier.padding(all = 16.dp),
                percentage = 81,
            )

            DashboardReviewItemContent(
                modifier = Modifier.padding(all = 16.dp),
                percentage = 60,
            )
        }
    }
}
