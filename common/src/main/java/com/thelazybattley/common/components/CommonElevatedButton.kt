package com.thelazybattley.common.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thelazybattley.common.ui.theme.textStyle

@Composable
fun CommonElevatedButton(
    modifier : Modifier = Modifier,
    @StringRes textRes: Int,
    colors : ButtonColors = ButtonDefaults.elevatedButtonColors(),
    isEnabled: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    onClick : () -> Unit
) {

    ElevatedButton(
        modifier = modifier.fillMaxWidth(),
        onClick = {
            onClick()
        },
        colors = colors,
        shape = RoundedCornerShape(size = 8.dp),
        contentPadding = PaddingValues(all = 16.dp),
        enabled = isEnabled
    ) {
        Text(
            text = stringResource(textRes),
            style = textStyle.poppins.copy(
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            ),
            maxLines = maxLines,
            overflow = TextOverflow.Ellipsis
        )
    }
}
