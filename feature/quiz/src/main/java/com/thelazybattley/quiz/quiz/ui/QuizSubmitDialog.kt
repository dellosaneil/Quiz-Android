package com.thelazybattley.quiz.quiz.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.thelazybattley.common.ui.theme.QuizAndroidTheme
import com.thelazybattley.common.ui.theme.colors
import com.thelazybattley.common.ui.theme.textStyle
import com.thelazybattley.quiz.R

@Composable
fun QuizSubmitDialog(
    modifier: Modifier = Modifier,
    showDialog: Boolean,
    onSubmitButtonClicked: () -> Unit,
    onContinueQuizClicked: () -> Unit
) {
    if (showDialog) {
        Dialog(
            onDismissRequest = {}
        ) {
            Column(
                modifier = modifier
                    .clip(shape = RoundedCornerShape(size = 16.dp))
                    .background(color = colors.white50)
                    .padding(horizontal = 16.dp, vertical = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(space = 16.dp)
            ) {
                Text(
                    text = stringResource(R.string.are_you_sure_you_want_to_submit),
                    style = textStyle.poppins.copy(
                        color = colors.black40,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp
                    ),
                    modifier = Modifier.padding(horizontal = 32.dp)
                )

                Text(
                    text = stringResource(R.string.congratulations_on_completing_the_quiz_would_you_like_to_submit_your_answers_now),
                    style = textStyle.poppins.copy(
                        color = colors.black40,
                        fontSize = 14.sp
                    ),
                    modifier = Modifier.padding(horizontal = 16.dp)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(space = 16.dp)
                ) {
                    ElevatedButton(
                        modifier = Modifier
                            .weight(1f),
                        onClick = {
                            onContinueQuizClicked()
                        },
                        colors = ButtonDefaults.elevatedButtonColors(
                            containerColor = colors.white30,
                            contentColor = colors.purple50
                        ),
                        shape = RoundedCornerShape(size = 8.dp),
                        contentPadding = PaddingValues(all = 12.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.close),
                            style = textStyle.poppins.copy(
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 16.sp
                            )
                        )
                    }


                    ElevatedButton(
                        modifier = Modifier
                            .weight(1f),
                        onClick = {
                            onSubmitButtonClicked()
                        },
                        colors = ButtonDefaults.elevatedButtonColors(
                            containerColor = colors.purple50,
                            contentColor = colors.white20
                        ),
                        shape = RoundedCornerShape(size = 8.dp),
                        contentPadding = PaddingValues(all = 12.dp)
                    ) {
                        Text(
                            text = stringResource(id = R.string.submit),
                            style = textStyle.poppins.copy(
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 16.sp
                            )
                        )
                    }
                }
            }
        }
    }
}


@SuppressLint("UnrememberedMutableState")
@Preview
@Composable
private fun PreviewQuizSubmitDialog() {
    QuizAndroidTheme {
        QuizSubmitDialog(
            modifier = Modifier,
            showDialog = true,
            onSubmitButtonClicked = {

            },
            onContinueQuizClicked = {

            }
        )
    }
}
