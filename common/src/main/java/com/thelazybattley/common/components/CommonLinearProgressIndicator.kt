package com.thelazybattley.common.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import com.thelazybattley.common.ui.theme.colors

@Composable
fun CommonLinearProgressIndicator(progress: Float) {
    LinearProgressIndicator(
        strokeCap = StrokeCap.Round,
        modifier = Modifier
            .height(
                height = 8.dp
            )
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        trackColor = colors.gray10,
        color = colors.purple40,
        progress = progress
    )
}
