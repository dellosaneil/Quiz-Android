package com.thelazybattley.common.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.unit.sp
import com.thelazybattley.common.R

val fontName = GoogleFont("Open Sans")

val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)

val fontFamily = FontFamily(
    Font(
        googleFont = fontName,
        fontProvider = provider
    )
)

data class QuizTypography(
    val xSmall: TextStyle = TextStyle(
        fontFamily = fontFamily,
        fontSize = 8.sp
    ),
    val small: TextStyle = TextStyle(
        fontFamily = fontFamily,
        fontSize = 12.sp

    ),
    val medium: TextStyle = TextStyle(
        fontFamily = fontFamily,
        fontSize = 16.sp

    ),
    val large: TextStyle = TextStyle(
        fontFamily = fontFamily,
        fontSize = 24.sp

    ),
    val xLarge: TextStyle = TextStyle(
        fontFamily = fontFamily,
        fontSize = 36.sp
    )
)

val LocalTypography = staticCompositionLocalOf<QuizTypography> { error("No color provided") }

val textStyle: QuizTypography
    @Composable
    @ReadOnlyComposable
    get() = LocalTypography.current

