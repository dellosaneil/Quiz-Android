package com.thelazybattley.common.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thelazybattley.common.ui.theme.QuizAndroidTheme
import com.thelazybattley.common.ui.theme.colors
import com.thelazybattley.common.ui.theme.textStyle
import kotlinx.coroutines.delay

@Composable
fun CommonBannerView(
    modifier: Modifier = Modifier,
    background: Color,
    text: String,
    onFinished: () -> Unit
) {
    val isVisible by produceState(initialValue = true) {
        delay(3_000L)
        value = false
        onFinished()
    }
    AnimatedVisibility(
        visible = isVisible,
        enter = slideInVertically(),
        exit = slideOutVertically()
    ) {
        BannerContent(
            modifier = modifier,
            background = background,
            text = text
        )

    }
}

@Composable
private fun BannerContent(
    modifier: Modifier,
    background: Color,
    text: String,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        colors = CardDefaults.cardColors(
            containerColor = background,
            contentColor = colors.white10
        ),
        shape = RoundedCornerShape(size = 16.dp)
    ) {
        Text(
            modifier = Modifier.padding(all = 16.dp),
            text = text,
            style = textStyle.poppins.copy(
                fontSize = 16.sp
            )
        )
    }
}

@Preview
@Composable
private fun PreviewBannerContent() {
    QuizAndroidTheme {
        BannerContent(
            modifier = Modifier,
            background = colors.red10,
            text = "Success"
        )
    }
}
