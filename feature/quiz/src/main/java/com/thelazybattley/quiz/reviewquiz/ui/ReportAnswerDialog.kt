package com.thelazybattley.quiz.reviewquiz.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
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
import com.thelazybattley.common.components.CommonElevatedButton
import com.thelazybattley.common.components.CommonTextField
import com.thelazybattley.common.ui.theme.colors
import com.thelazybattley.common.ui.theme.textStyle
import com.thelazybattley.quiz.R
import com.thelazybattley.quiz.reviewquiz.ReportAnswerState

@Composable
fun ReportAnswerDialog(
    modifier: Modifier = Modifier,
    reportAnswerState: ReportAnswerState,
    onValueChanged: (String) -> Unit,
    onCancelClicked: () -> Unit,
    onReportClicked: () -> Unit
) {
    if (reportAnswerState.showReportDialog) {
        Dialog(onDismissRequest = {}) {
            Column(
                modifier = modifier
                    .clip(shape = RoundedCornerShape(size = 16.dp))
                    .background(color = colors.white50)
                    .padding(horizontal = 16.dp, vertical = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(space = 16.dp)
            ) {
                Text(
                    text = stringResource(R.string.report_incorrect_answer),
                    style = textStyle.poppins.copy(
                        color = colors.black40,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp
                    ),
                    modifier = Modifier.padding(horizontal = 32.dp)
                )

                CommonTextField(
                    modifier = Modifier,
                    text = reportAnswerState.text,
                    singleLine = true,
                    placeholderText = stringResource(R.string.enter_the_correct_answer),
                    onValueChange = { text ->
                        onValueChanged(text)
                    }
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(space = 16.dp)
                ) {
                    CommonElevatedButton(
                        modifier = Modifier
                            .weight(1f),
                        onClick = {
                            onCancelClicked()
                        },
                        colors = ButtonDefaults.elevatedButtonColors(
                            containerColor = colors.white30,
                            contentColor = colors.purple50
                        ),
                        textRes = R.string.cancel
                    )


                    CommonElevatedButton(
                        modifier = Modifier
                            .weight(1f),
                        onClick = {
                            onReportClicked()
                        },
                        colors = ButtonDefaults.elevatedButtonColors(
                            containerColor = colors.purple50,
                            contentColor = colors.white20
                        ),
                        textRes = R.string.report,
                        isEnabled = reportAnswerState.text.isNotEmpty()
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreviewReportAnswerDialog() {
    ReportAnswerDialog(
        reportAnswerState = ReportAnswerState(showReportDialog = true),
        onCancelClicked = {},
        onValueChanged = {

        }
    ) {

    }
}
