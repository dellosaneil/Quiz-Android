package com.thelazybattley.dashboard.dashboard.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thelazybattley.common.ui.theme.textStyle
import com.thelazybattley.dashboard.R

@Composable
fun DashboardLabel(
    modifier: Modifier = Modifier,
    label: String,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .clip(shape = RoundedCornerShape(size = 8.dp))
            .padding(all = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(space = 4.dp)
    ) {
        Text(
            text = label,
            style = textStyle.poppins.copy(
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
        )
        Spacer(
            modifier = Modifier
                .height(16.dp)
                .weight(1f)
        )
        Text(
            text = stringResource(R.string.see_all),
            style = textStyle.poppins.copy(
                fontSize = 14.sp
            ),
            modifier = Modifier
                .clip(shape = RoundedCornerShape(size = 8.dp))
                .clickable {
                    onClick()
                }
                .padding(all = 8.dp)
        )
    }
}

@Preview
@Composable
private fun PreviewDashboardLabel() {
    DashboardLabel(
        label = stringResource(R.string.life_of_rizal_quiz)
    ) {

    }
}
