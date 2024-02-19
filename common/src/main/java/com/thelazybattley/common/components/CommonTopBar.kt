package com.thelazybattley.common.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
    @DrawableRes navigationIconRes: Int? = null,
    actions: @Composable (() -> Unit)? = null,
    @StringRes titleRes: Int,
    background: Color = colors.transparent,
    onNavClick: (() -> Unit)? = null
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
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp
                    )
                )
            },
            navigationIcon = {
                if (navigationIconRes != null) {
                    IconButton(
                        modifier = Modifier
                            .padding(start = 16.dp)
                            .size(24.dp),
                        onClick = {
                            if (onNavClick != null) {
                                onNavClick()
                            }
                        }
                    ) {
                        Icon(
                            painter = painterResource(id = navigationIconRes),
                            contentDescription = ""

                        )
                    }
                }
            },
            actions = {
                if (actions != null) {
                    actions()
                    Spacer(modifier = Modifier.width(width = 16.dp))
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = background
            )
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
