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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
                            .align(alignment = Alignment.CenterHorizontally)
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
    DashboardQuizItems(
        modifier = Modifier.padding(all = 16.dp),
        categories = listOf(
            CategoryDetail(
                count = 10,
                category = "People"
            ),
            CategoryDetail(
                count = 1,
                category = "Places"
            ),
            CategoryDetail(
                count = 3,
                category = "Relationship"
            )
        ),
        drawRes = com.thelazybattley.common.R.drawable.img_timed_quiz
    ) {

    }
}
