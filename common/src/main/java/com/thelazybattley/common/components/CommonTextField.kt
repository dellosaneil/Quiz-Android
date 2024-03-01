package com.thelazybattley.common.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thelazybattley.common.ui.theme.QuizAndroidTheme
import com.thelazybattley.common.ui.theme.colors
import com.thelazybattley.common.ui.theme.textStyle

@Composable
fun CommonTextField(
    keyboardActions: KeyboardActions =  KeyboardActions.Default,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    modifier: Modifier,
    text: String,
    singleLine: Boolean,
    placeholderText: String,
    onValueChange: (String) -> Unit,
) {
    TextField(
        modifier = modifier
            .border(
                border = BorderStroke(
                    width = 1.dp,
                    color = colors.black50
                ),
                shape = RoundedCornerShape(size = 8.dp)
            ),
        value = text,
        onValueChange = {
            onValueChange(it)
        },
        textStyle = textStyle.poppins.copy(
            fontSize = 14.sp
        ),
        singleLine = singleLine,
        shape = RoundedCornerShape(size = 8.dp),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = colors.white50,
            unfocusedContainerColor = colors.white50,
            unfocusedIndicatorColor = colors.transparent,
            focusedIndicatorColor = colors.transparent
        ),
        placeholder = {
            Text(
                text = placeholderText,
                style = textStyle.poppins.copy(
                    fontSize = 14.sp
                )
            )
        },
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewCommonTextField() {
    QuizAndroidTheme {
        Column(verticalArrangement = Arrangement.spacedBy(space = 16.dp)) {
            CommonTextField(
                modifier = Modifier.padding(all = 16.dp),
                text = "words",
                singleLine = true,
                placeholderText = "Write something",
                onValueChange = {

                }
            )

            CommonTextField(
                modifier = Modifier.padding(all = 16.dp),
                text = "",
                singleLine = true,
                placeholderText = "Write something",
                onValueChange = {

                }
            )
        }

    }
}
