package com.thelazybattley.dashboard.dashboard.ui

import android.net.Uri
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavOptions
import com.google.gson.Gson
import com.thelazybattley.common.components.CommonTopBar
import com.thelazybattley.common.enums.QuizType
import com.thelazybattley.common.model.AppScreens
import com.thelazybattley.common.model.Question
import com.thelazybattley.common.ui.theme.QuizAndroidTheme
import com.thelazybattley.common.ui.theme.colors
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
        viewModel.initialize(quizType = QuizType.LIFE_OF_RIZAL)
        viewModel.initialize(quizType = QuizType.EL_FILI)
        viewModel.initialize(quizType = QuizType.NOLI_ME_TANGERE)
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
                title = stringResource(id = R.string.home),
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
                DashboardLabel(
                    modifier = Modifier,
                    label = stringResource(R.string.life_of_rizal_quiz)
                ) {
                    navigate(
                        AppScreens.QuizConfigScreen.args(
                            category = null,
                            type = QuizType.LIFE_OF_RIZAL.type
                        ), null
                    )
                }
                DashboardQuizItems(
                    categories = uiState.lifeOfRizalCategories,
                    drawRes = com.thelazybattley.common.R.drawable.img_timed_quiz
                ) { category ->
                    navigate(
                        AppScreens.QuizConfigScreen.args(
                            category = category,
                            type = QuizType.LIFE_OF_RIZAL.type
                        ), null
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(8.dp))
            }

            item {
                DashboardLabel(
                    label = stringResource(R.string.noli_me_tangere_quiz)
                ) {
                    navigate(
                        AppScreens.QuizConfigScreen.args(
                            category = null,
                            type = QuizType.NOLI_ME_TANGERE.type
                        ), null
                    )
                }
                DashboardQuizItems(
                    categories = uiState.noliCategories,
                    drawRes = R.drawable.noli
                ) { category ->
                    navigate(
                        AppScreens.QuizConfigScreen.args(
                            category = category,
                            type = QuizType.NOLI_ME_TANGERE.type
                        ), null
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(8.dp))
            }

            item {
                DashboardLabel(
                    label = stringResource(R.string.el_filibusterismo_quiz)
                ) {
                    navigate(
                        AppScreens.QuizConfigScreen.args(
                            category = null,
                            type = QuizType.EL_FILI.type
                        ), null
                    )
                }
                DashboardQuizItems(
                    categories = uiState.elFiliCategories,
                    drawRes = com.thelazybattley.common.R.drawable.img_el_fili
                ) { category ->
                    navigate(
                        AppScreens.QuizConfigScreen.args(
                            category = category,
                            type = QuizType.EL_FILI.type
                        ), null
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(8.dp))
            }

            item {
                DashboardLabel(label = stringResource(R.string.review_previous_quiz_result)) {
                    navigate(AppScreens.QuizResultsHistoryScreen.route, null)
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
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewDashboardScreen() {
    QuizAndroidTheme {
        DashboardScreen(
            uiState = DashboardUiState().copy(
                lifeOfRizalCategories = listOf(
                    CategoryDetail(
                        count = 3,
                        category = "Adulthood"
                    ),
                ),
                noliCategories = listOf(
                    CategoryDetail(
                        count = 3,
                        category = "Chapter 1"
                    ),
                ),
                elFiliCategories = listOf(
                    CategoryDetail(
                        count = 3,
                        category = "Chapter 1"
                    ),
                ),
                quizResults = listOf(
                    QuizResult(
                        questions = listOf(
                            Question(
                                questionId = 1,
                                question = "",
                                answer = "",
                                choices = emptyList(),
                                category = "Adulthood",
                                quizType = QuizType.LIFE_OF_RIZAL,
                            ),
                        ),
                        chosenAnswers = listOf(""),
                        answers = listOf(""),
                        type = QuizType.NOLI_ME_TANGERE,
                        category = "Chapter 2",
                        percent = 99
                    ),
                    QuizResult(
                        questions = listOf(
                            Question(
                                questionId = 2,
                                question = "",
                                answer = "",
                                choices = emptyList(),
                                category = "Adulthood",
                                quizType = QuizType.LIFE_OF_RIZAL
                            ),
                            Question(
                                questionId = 1,
                                question = "",
                                answer = "",
                                choices = emptyList(),
                                category = "Adulthood",
                                quizType = QuizType.LIFE_OF_RIZAL
                            ),
                        ),
                        chosenAnswers = listOf(""),
                        answers = listOf(""),
                        type = QuizType.NOLI_ME_TANGERE,
                        category = "Chapter 2",
                        percent = 99
                    )
                )
            )
        ) { route, options ->

        }
    }
}
