package com.thelazybattley.dashboard.dashboard.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thelazybattley.common.ui.theme.QuizAndroidTheme
import com.thelazybattley.dashboard.R
import com.thelazybattley.domain.model.CategoryDetail

@Composable
fun DashboardQuizItems(
    modifier: Modifier = Modifier,
    categories: List<CategoryDetail>,
    @DrawableRes drawRes: Int,
    onClick: (String) -> Unit
) {
    LazyRow(modifier = modifier) {
        items(
            items = categories,
            key = { it.category }
        ) { details ->
            DashboardItem(
                modifier = Modifier,
                title = details.category,
                description = pluralStringResource(
                    com.thelazybattley.common.R.plurals.questions,
                    details.count,
                    details.count,
                ),
                content = {
                    Image(
                        painter = painterResource(id = drawRes),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(vertical = 8.dp)
                            .width(width = 56.dp)
                            .align(alignment = Alignment.CenterHorizontally),
                        contentScale = ContentScale.FillWidth
                    )
                }
            ) {
                onClick(details.category)
            }
        }
    }
}

@Preview
@Composable
private fun PreviewDashboardQuizItems() {
    QuizAndroidTheme {
        DashboardQuizItems(
            modifier = Modifier.padding(all = 16.dp),
            categories = listOf(
                CategoryDetail(
                    count = 10,
                    category = "People",
                    answeredCount = 3,
                    progress = 0.33f
                ),
                CategoryDetail(
                    count = 7,
                    category = "Places",
                    answeredCount = 3,
                    progress = 0.275f
                ),
                CategoryDetail(
                    count = 3,
                    category = "Relationship",
                    answeredCount = 3,
                    progress = 1f
                )
            ),
            drawRes = R.drawable.ic_quiz
        ) {

        }
    }
}
