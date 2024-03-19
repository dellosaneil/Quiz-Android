package com.thelazybattley.dashboard.dashboard.ui

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.painterResource
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
import com.thelazybattley.dashboard.BuildConfig
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
                title = stringResource(id = R.string.home)
            )
        },
        containerColor = colors.white60
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.padding(paddingValues),
            contentPadding = PaddingValues(all = 16.dp)
        ) {

            item {
                if (uiState.isLoading) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        CircularProgressIndicator(
                            color = colors.purple50,
                            strokeWidth = 4.dp,
                            strokeCap = StrokeCap.Round
                        )
                    }
                    return@item
                }

                DashboardLabel(
                    modifier = Modifier,
                    label = stringResource(R.string.life_of_rizal_quiz),
                    showSeeAll = uiState.lifeOfRizalCategories.isNotEmpty()
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
                    drawRes = R.drawable.ic_quiz
                ) { category ->
                    navigate(
                        AppScreens.QuizConfigScreen.args(
                            category = category,
                            type = QuizType.LIFE_OF_RIZAL.type
                        ), null
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                DashboardLabel(
                    label = stringResource(R.string.noli_me_tangere_quiz),
                    showSeeAll = uiState.noliCategories.isNotEmpty()
                ) {
                    navigate(
                        AppScreens.QuizConfigScreen.args(
                            category = null,
                            type = QuizType.NOLI_ME_TANGERE.type
                        ), null
                    )
                }
                if (uiState.noliCategories.isNotEmpty()) {
                    DashboardQuizItems(
                        categories = uiState.noliCategories,
                        drawRes = R.drawable.img_noli
                    ) { category ->
                        navigate(
                            AppScreens.QuizConfigScreen.args(
                                category = category,
                                type = QuizType.NOLI_ME_TANGERE.type
                            ), null
                        )
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                DashboardLabel(
                    label = stringResource(R.string.el_filibusterismo_quiz),
                    showSeeAll = uiState.elFiliCategories.isNotEmpty(),
                    onClick = {
                        navigate(
                            AppScreens.QuizConfigScreen.args(
                                category = null,
                                type = QuizType.EL_FILI.type
                            ), null
                        )
                    }
                )
                if (uiState.elFiliCategories.isNotEmpty()) {
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
                    Spacer(modifier = Modifier.height(8.dp))
                }

                if (uiState.quizResults.isNotEmpty()) {
                    DashboardLabel(
                        showSeeAll = true,
                        label = stringResource(R.string.review_previous_quiz_result),
                        onClick = {
                            navigate(AppScreens.QuizResultsHistoryScreen.route, null)
                        }
                    )
                    LazyRow {
                        items(items = uiState.quizResults) { result ->
                            DashboardItem(
                                modifier = Modifier,
                                title = result.category,
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
                Spacer(modifier = Modifier.height(8.dp))

                if (BuildConfig.DEBUG) {
                    DashboardLabel(
                        label = stringResource(R.string.admin),
                        showSeeAll = false
                    )
                    LazyRow {
                        item {
                            DashboardItem(
                                modifier = Modifier,
                                title = stringResource(R.string.add_new_question),
                                description = stringResource(R.string.input_a_question),
                                content = {
                                    Image(
                                        painter = painterResource(id = R.drawable.ic_light_bulb),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .padding(vertical = 8.dp)
                                            .width(width = 56.dp)
                                            .align(alignment = Alignment.CenterHorizontally)
                                    )
                                }
                            ) {
                                navigate(AppScreens.CreateQuestionScreen.route, null)
                            }
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
                isLoading = false,
                lifeOfRizalCategories = listOf(
                    CategoryDetail(
                        count = 3,
                        category = "Adulthood",
                        answeredCount = 3,
                        progress = 1f
                    ),
                ),
                noliCategories = listOf(
                    CategoryDetail(
                        count = 3,
                        category = "Chapter 1",
                        answeredCount = 3,
                        progress = 1f
                    ),
                ),
                elFiliCategories = listOf(
                    CategoryDetail(
                        count = 3,
                        category = "Chapter 1",
                        answeredCount = 3,
                        progress = 1f
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
        ) { _, _ ->

        }
    }
}
