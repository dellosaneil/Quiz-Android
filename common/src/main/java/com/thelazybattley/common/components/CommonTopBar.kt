package com.thelazybattley.common.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thelazybattley.common.R
import com.thelazybattley.common.ui.theme.colors
import com.thelazybattley.common.ui.theme.textStyle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommonTopBar(
    modifier: Modifier = Modifier,
    @StringRes titleRes: Int,
    @DrawableRes navigationIconRes: Int?,
    actions: @Composable (() -> Unit)? = null,
    onNavClick: () -> Unit
) {

    Column {
        TopAppBar(
            modifier = modifier,
            title = {
                Text(
                    modifier = Modifier,
                    text = stringResource(id = titleRes),
                    style = textStyle.poppins.copy(
                        color = colors.black30,
                        fontWeight = FontWeight.Medium,
                        fontSize = 18.sp
                    )
                )
            },
            navigationIcon = {
                if (navigationIconRes != null) {
                    Icon(
                        painter = painterResource(navigationIconRes),
                        contentDescription = "",
                        modifier = Modifier
                            .padding(start = 16.dp)
                            .size(24.dp)
                            .clickable {
                                onNavClick()
                            }

                    )
                }
            },
            actions = {
                if (actions != null) {
                    actions()
                    Spacer(modifier = Modifier.width(width = 16.dp))
                }
            }
        )
        Divider(thickness = 1.dp)
    }
}

@Preview
@Composable
fun PreviewCommonTopBar() {
    CommonTopBar(
        titleRes = R.string.quiz,
        navigationIconRes = R.drawable.ic_back_arrow
    ) {

    }
}
