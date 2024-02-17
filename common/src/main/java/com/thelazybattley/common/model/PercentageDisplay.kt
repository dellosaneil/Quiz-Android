package com.thelazybattley.common.model

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color

data class PercentageDisplay(
    @DrawableRes val drawRes: Int,
    val textColor: Color,
    val text: String
)
