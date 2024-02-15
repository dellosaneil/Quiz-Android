package com.thelazybattley.dashboard.dashboard.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavOptions
import com.thelazybattley.common.components.CommonTopBar
import com.thelazybattley.common.enums.QuestionCategory
import com.thelazybattley.common.model.AppScreens
import com.thelazybattley.common.ui.theme.QuizAndroidTheme
import com.thelazybattley.common.ui.theme.colors
import com.thelazybattley.common.ui.theme.textStyle
import com.thelazybattley.dashboard.R
import com.thelazybattley.dashboard.dashboard.DashboardEvents
import com.thelazybattley.dashboard.dashboard.DashboardUiState
import com.thelazybattley.dashboard.dashboard.DashboardViewModel
import com.thelazybattley.domain.model.CategoryDetail

@Composable
fun DashboardScreen(
    viewModel: DashboardViewModel = hiltViewModel(),
    navigate: (String, NavOptions?) -> Unit
) {
    val uiState by viewModel.state.collectAsState()
    val events by viewModel.events.collectAsState(initial = null)
    DashboardScreen(
        uiState = uiState,
        events = events,
        navigate = navigate
    )
}


@Composable
fun DashboardScreen(
    uiState: DashboardUiState,
    events: DashboardEvents?,
    navigate: (String, NavOptions?) -> Unit
) {
    Scaffold(
        topBar = {
            CommonTopBar(titleRes = R.string.home,
                background = colors.white50)
        },
        containerColor = colors.white60

    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.padding(all = 16.dp),
            contentPadding = paddingValues
        ) {
            item {
                Row(
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(size = 8.dp))
                        .clickable {

                        }
                        .padding(all = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(space = 4.dp)
                ) {
                    Text(
                        text = stringResource(R.string.take_quiz),
                        style = textStyle.poppins.copy(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium
                        )
                    )
                    Icon(
                        painter = painterResource(id = com.thelazybattley.common.R.drawable.ic_back),
                        contentDescription = null,
                        modifier = Modifier
                            .rotate(degrees = 180f)
                            .size(size = 16.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
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
                            )
                        ) {
                            navigate(AppScreens.QuizScreen.route, null)
                        }
                    }
                }
            }

            item {
                Row(
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(size = 8.dp))
                        .clickable {
                            navigate(AppScreens.QuizScreen.route, null)
                        }
                        .padding(all = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(space = 4.dp)
                ) {
                    Text(
                        text = "Review Previous Quiz",
                        style = textStyle.poppins.copy(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium
                        )
                    )
                    Icon(
                        painter = painterResource(id = com.thelazybattley.common.R.drawable.ic_back),
                        contentDescription = null,
                        modifier = Modifier
                            .rotate(degrees = 180f)
                            .size(size = 16.dp)

                    )

                }
                LazyRow {
                    item {
                        Spacer(modifier = Modifier.height(64.dp))
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
                categoriesDetails = listOf(
                    CategoryDetail(count = 3, category = QuestionCategory.ADULTHOOD),
                    CategoryDetail(count = 5, category =  QuestionCategory.IMPORTANT_PLACES),
                    CategoryDetail(count = 5, category =  QuestionCategory.RELATIONSHIP),
                    CategoryDetail(count = 5, category =  QuestionCategory.PEOPLE),
                )
            ),
            events = null
        ) { route, options ->

        }
    }
}
