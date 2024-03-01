package com.thelazybattley.feedback.createquestion.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.thelazybattley.common.components.CommonElevatedButton
import com.thelazybattley.common.components.CommonFilterChip
import com.thelazybattley.common.components.CommonTextField
import com.thelazybattley.common.components.CommonTopBar
import com.thelazybattley.common.enums.QuizType
import com.thelazybattley.common.ext.toTitleCase
import com.thelazybattley.common.ui.theme.QuizAndroidTheme
import com.thelazybattley.common.ui.theme.colors
import com.thelazybattley.common.ui.theme.textStyle
import com.thelazybattley.feedback.R
import com.thelazybattley.feedback.createquestion.CreateQuestionCallbacks
import com.thelazybattley.feedback.createquestion.CreateQuestionUiState
import com.thelazybattley.feedback.createquestion.CreateQuestionViewModel

@Composable
fun CreateQuestionScreen(
    onPopBackStack: () -> Unit
) {
    val viewModel = hiltViewModel<CreateQuestionViewModel>()
    val uiState by viewModel.state.collectAsState()

    CreateQuestionScreen(
        uiState = uiState,
        callbacks = viewModel,
        onPopBackStack = onPopBackStack
    )
}

@Composable
private fun CreateQuestionScreen(
    uiState: CreateQuestionUiState,
    callbacks: CreateQuestionCallbacks,
    onPopBackStack: () -> Unit
) {

    Scaffold(
        topBar = {
            CommonTopBar(
                title = stringResource(R.string.create_new_question),
                navigationIconRes = com.thelazybattley.common.R.drawable.ic_back_arrow
            ) {
                onPopBackStack()
            }
        }
    ) { paddingValues ->
        val focusRequester = remember { FocusRequester() }
        val focusManager = LocalFocusManager.current

        Column(
            modifier = Modifier.padding(paddingValues = paddingValues)
        ) {
            CommonTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 16.dp)
                    .focusRequester(focusRequester = focusRequester),
                text = uiState.question,
                singleLine = true,
                placeholderText = stringResource(R.string.input_question_here),
                onValueChange = { callbacks.updateQuestion(text = it) },
                keyboardActions = KeyboardActions {
                    focusManager.moveFocus(focusDirection = FocusDirection.Next)
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                )
            )

            HorizontalDivider()

            Text(
                text = stringResource(R.string.choices),
                modifier = Modifier.padding(start = 8.dp, top = 8.dp),
                style = textStyle.poppins.copy(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = colors.black50
                )
            )

            CommonTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 16.dp)
                    .focusRequester(focusRequester = focusRequester),
                text = uiState.choices[0].orEmpty(),
                singleLine = true,
                placeholderText = stringResource(R.string.input_correct_answer),
                onValueChange = { callbacks.updateChoices(text = it, index = 0) },
                keyboardActions = KeyboardActions {
                    focusManager.moveFocus(focusDirection = FocusDirection.Next)
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                )
            )

            CommonTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 16.dp)
                    .focusRequester(focusRequester = focusRequester),
                text = uiState.choices[1].orEmpty(),
                singleLine = true,
                placeholderText = stringResource(R.string.input_choice),
                onValueChange = { callbacks.updateChoices(text = it, index = 1) },
                keyboardActions = KeyboardActions {
                    focusManager.moveFocus(focusDirection = FocusDirection.Next)
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                )
            )

            CommonTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 16.dp)
                    .focusRequester(focusRequester = focusRequester),
                text = uiState.choices[2].orEmpty(),
                singleLine = true,
                placeholderText = stringResource(R.string.input_choice),
                onValueChange = { callbacks.updateChoices(text = it, index = 2) },
                keyboardActions = KeyboardActions {
                    focusManager.moveFocus(focusDirection = FocusDirection.Next)
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                )
            )

            CommonTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 16.dp)
                    .focusRequester(focusRequester = focusRequester),
                text = uiState.choices[3].orEmpty(),
                singleLine = true,
                placeholderText = stringResource(R.string.input_choice),
                onValueChange = { callbacks.updateChoices(text = it, index = 3) },
                keyboardActions = KeyboardActions {
                    focusManager.moveFocus(focusDirection = FocusDirection.Next)
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                )
            )
            HorizontalDivider()
            Text(
                text = stringResource(R.string.category),
                modifier = Modifier.padding(start = 8.dp, top = 8.dp),
                style = textStyle.poppins.copy(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = colors.black50
                )
            )

            CommonTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 16.dp)
                    .focusRequester(focusRequester = focusRequester),
                text = uiState.category,
                singleLine = true,
                placeholderText = stringResource(R.string.input_category),
                onValueChange = { callbacks.setCategory(category = it) },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions {
                    focusManager.clearFocus()
                }
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(space = 8.dp)
            ) {
                QuizType.entries.forEach { quizType ->
                    CommonFilterChip(
                        modifier = Modifier.weight(1f),
                        text = quizType.name.toTitleCase(),
                        isSelected = uiState.quizType == quizType
                    ) {
                        callbacks.setQuizType(quizType = quizType)
                    }
                }
            }


            Spacer(modifier = Modifier.weight(1f))
            CommonElevatedButton(
                textRes = R.string.submit,
                modifier = Modifier.padding(all = 16.dp),
                colors = ButtonDefaults.elevatedButtonColors(
                    containerColor = colors.purple50,
                    contentColor = colors.white20
                )
            ) {
                callbacks.submitQuestion()
            }
        }
    }


}

@Preview
@Composable
private fun PreviewCreateQuestionScreen() {
    QuizAndroidTheme {
        CreateQuestionScreen(
            uiState = CreateQuestionUiState(),
            callbacks = CreateQuestionCallbacks.default()
        ) {

        }
    }
}
