package com.thelazybattley.common.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thelazybattley.common.ui.theme.QuizAndroidTheme
import com.thelazybattley.common.ui.theme.colors
import com.thelazybattley.common.ui.theme.textStyle

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CommonFilterChip(
    modifier: Modifier,
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val (background, style, borderColor) = if (isSelected) {
        Triple(
            first = colors.purple30,
            second = textStyle.poppins.copy(
                fontSize = 14.sp,
                color = colors.purple50
            ),
            third = colors.purple50
        )
    } else {
        Triple(
            first = colors.white10,
            second = textStyle.poppins.copy(
                fontSize = 14.sp,
                color = colors.black20
            ),
            third = colors.gray20
        )
    }


    FilterChip(
        modifier = modifier.padding(horizontal = 4.dp),
        selected = isSelected,
        onClick = { onClick() },
        label = {
            Text(
                text = text,
                style = style,
                maxLines = 1,
                modifier = Modifier
                    .basicMarquee()
                    .padding(vertical = 8.dp)
            )
        },
        colors = FilterChipDefaults.filterChipColors(
            selectedContainerColor = background,
            containerColor = background
        ),
        border = BorderStroke(width = 1.dp, color = borderColor)

    )
}

@Preview(showBackground = true)
@Composable
fun PreviewCommonFilterChip() {
    QuizAndroidTheme {
        Column {
            CommonFilterChip(
                modifier = Modifier.padding(16.dp),
                text = "People",
                isSelected = true,
                onClick = {}
            )

            CommonFilterChip(
                modifier = Modifier.padding(16.dp),
                text = "People",
                isSelected = false,
                onClick = {}
            )
        }

    }
}
