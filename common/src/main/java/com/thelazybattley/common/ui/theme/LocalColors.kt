package com.thelazybattley.common.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class LightColors(
    val purple10: Color = Purple10,
    val purple20: Color = Purple20,
    val purple30: Color = Purple30,
    val purple40: Color = Purple40,
    val purple50: Color = Purple50,
    val white10: Color = White10,
    val white20: Color = White20,
    val white30: Color = White30,
    val white50: Color = White50,
    val pink30: Color = Pink30,
    val gray10: Color = Gray10,
    val gray20: Color = Gray20,
    val gray30: Color = Gray30,
    val pink10: Color = Pink10,
    val black50: Color = Black50,
    val black40: Color = Black40,
    val black30: Color = Black30,
    val black20: Color = Black20,
    val blackOpacity50: Color = BlackOpacity50,
    val transparent: Color = Transparent,
    val green10 : Color = Green10,
    val green30: Color = Green30,
    val green50: Color = Green50,
    val red10: Color= Red10,
    val red30: Color = Red30,
    val red40: Color = Red40,
)

val LocalColors = staticCompositionLocalOf<LightColors> { error("No color provided") }

val colors: LightColors
    @Composable
    @ReadOnlyComposable
    get() = LocalColors.current


