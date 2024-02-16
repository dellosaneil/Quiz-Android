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

val poppinsFontName = GoogleFont("Poppins")

val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)

val poppinsFontFamily = FontFamily(
    Font(
        googleFont = poppinsFontName,
        fontProvider = provider
    )
)

data class QuizTypography(
    val poppins : TextStyle = TextStyle(
        fontFamily = poppinsFontFamily,
        fontSize = 18.sp
    )
)

val LocalTypography = staticCompositionLocalOf<QuizTypography> { error("No color provided") }

val textStyle: QuizTypography
    @Composable
    @ReadOnlyComposable
    get() = LocalTypography.current

