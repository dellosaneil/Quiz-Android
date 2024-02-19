package com.thelazybattley.dashboard.dashboard.ui

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavOptions
import com.google.gson.Gson
import com.thelazybattley.common.components.CommonTopBar
import com.thelazybattley.common.enums.QuestionCategory
import com.thelazybattley.common.model.AppScreens
import com.thelazybattley.common.model.Question
import com.thelazybattley.common.ui.theme.QuizAndroidTheme
import com.thelazybattley.common.ui.theme.colors
import com.thelazybattley.common.ui.theme.textStyle
import com.thelazybattley.dashboard.R
import com.thelazybattley.dashboard.dashboard.DashboardUiState
import com.thelazybattley.dashboard.dashboard.DashboardViewModel
import com.thelazybattley.domain.mapper.toQuizDetailsState
import com.thelazybattley.domain.model.CategoryDetail
import com.thelazybattley.domain.model.QuizResult

@Composable
fun DashboardScreen(
    viewModel: DashboardViewModel = hiltViewModel(),
    navigate: (String, NavOptions?) -> Unit
) {
    val uiState by viewModel.state.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.initialize()
    }
    DashboardScreen(
        uiState = uiState,
        navigate = navigate,
    )
}


@Composable
fun DashboardScreen(
    uiState: DashboardUiState,
    navigate: (String, NavOptions?) -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CommonTopBar(
                titleRes = R.string.home,
                background = colors.white50
            )
        },
        containerColor = colors.white60
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.padding(paddingValues),
            contentPadding = PaddingValues(all = 16.dp)
        ) {
            item {
                Row(
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(size = 8.dp))
                        .padding(all = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(space = 4.dp)
                ) {
                    Text(
                        text = stringResource(R.string.take_quiz),
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
                                navigate(AppScreens.QuizConfigScreen.args(null), null)
                            }
                            .padding(all = 8.dp)
                    )
                }
                LazyRow {
                    items(
                        items = uiState.categoriesDetails,
                        key = { it.category }
                    ) { details ->
                        DashboardItem(
                            modifier = Modifier,
                            title = details.category.toString(),
                            description = pluralStringResource(
                                com.thelazybattley.common.R.plurals.questions,
                                details.count,
                                details.count,
                            ),
                            content = {
                                Image(
                                    painter = painterResource(id = com.thelazybattley.common.R.drawable.img_timed_quiz),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .padding(vertical = 8.dp)
                                        .size(size = 56.dp)
                                        .align(alignment = Alignment.CenterHorizontally)
                                )
                            }
                        ) {
                            navigate(
                                AppScreens.QuizConfigScreen.args(
                                    category = details.category.name
                                ), null
                            )
                        }
                    }
                }
            }

            item {
                Row(
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(size = 8.dp))
                        .padding(all = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(space = 4.dp)
                ) {
                    Text(
                        text = stringResource(R.string.review_previous_quiz_result),
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
                                navigate(AppScreens.QuizResultsHistoryScreen.route, null)
                            }
                            .padding(all = 8.dp)
                    )

                }
                LazyRow {
                    items(items = uiState.quizResults) { result ->
                        DashboardItem(
                            modifier = Modifier,
                            title = result.category.toString(),
                            description = pluralStringResource(
                                com.thelazybattley.common.R.plurals.questions,
                                result.questions.size,
                                result.questions.size,
                            ),
                            content = {
                                DashboardReviewItemContent(
                                    modifier = Modifier.padding(vertical = 8.dp),
                                    percentage = result.percent
                                )
                            }
                        ) {
                            val json = Uri.encode(Gson().toJson(result.toQuizDetailsState))
                            navigate(AppScreens.ReviewScreen.args(json = json), null)
                        }
                    }
                }
            }
            item {
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewDashboardScreen() {
    QuizAndroidTheme {
        DashboardScreen(
            uiState = DashboardUiState().copy(
                categoriesDetails = listOf(
                    CategoryDetail(count = 3, category = QuestionCategory.ADULTHOOD),
                    CategoryDetail(count = 5, category = QuestionCategory.IMPORTANT_PLACES),
                    CategoryDetail(count = 5, category = QuestionCategory.RELATIONSHIP),
                    CategoryDetail(count = 5, category = QuestionCategory.PEOPLE),
                ),
                quizResults = listOf(
                    QuizResult(
                        questions = listOf(
                            Question(
                                id = 1,
                                question = "",
                                answer = "",
                                choices = emptyList(),
                                category = QuestionCategory.PEOPLE
                            ),
                            Question(
                                id = 1,
                                question = "",
                                answer = "",
                                choices = emptyList(),
                                category = QuestionCategory.OTHERS
                            ),
                            Question(
                                id = 1,
                                question = "",
                                answer = "",
                                choices = emptyList(),
                                category = QuestionCategory.OTHERS
                            ),
                        )
                    ),
                    QuizResult(
                        questions = listOf(
                            Question(
                                id = 2,
                                question = "",
                                answer = "",
                                choices = emptyList(),
                                category = QuestionCategory.RELATIONSHIP
                            ),
                            Question(
                                id = 1,
                                question = "",
                                answer = "",
                                choices = emptyList(),
                                category = QuestionCategory.OTHERS
                            ),
                        )
                    )
                )
            )
        ) { route, options ->

        }
    }
}
