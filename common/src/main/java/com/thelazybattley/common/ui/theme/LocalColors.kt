package com.thelazybattley.common.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class LightColors(
    val purple10: Color = Purple10,
    val purple50: Color = Purple50,
    val white50: Color = White50,
    val pink30: Color = Pink30,
    val gray30: Color = Gray30,
    val pink10: Color = Pink10
)

val LocalColors = staticCompositionLocalOf<LightColors> { error("No color provided") }

val colors : LightColors
    @Composable
    @ReadOnlyComposable
    get() = LocalColors.current


