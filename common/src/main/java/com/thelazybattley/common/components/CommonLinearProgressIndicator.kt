package com.thelazybattley.common.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.thelazybattley.common.ui.theme.colors

@Composable
fun CommonLinearProgressIndicator(
    modifier: Modifier = Modifier,
    height: Dp = 8.dp,
    paddingValues: PaddingValues = PaddingValues(horizontal = 16.dp),
    progress: Float
) {
    LinearProgressIndicator(
        progress = {
            progress
        },
        modifier = modifier
            .height(
                height = height
            )
            .fillMaxWidth()
            .padding(paddingValues),
        color = colors.purple40,
        trackColor = colors.gray10,
        strokeCap = StrokeCap.Round,
    )
}
