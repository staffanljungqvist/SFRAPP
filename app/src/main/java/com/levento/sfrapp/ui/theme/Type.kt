package com.levento.sfrapp.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.levento.sfrapp.R


private val fontFamilyMontsserat = FontFamily(
    listOf(
        Font(
            resId = R.font.montserat_regular,
            weight = FontWeight.Normal
        ),
        Font(
            resId = R.font.montserat_bold,
            weight = FontWeight.Bold
        )
    )
)

private val fontFamilySourceSansPro = FontFamily(
    listOf(
        Font(
            resId = R.font.sourcesanspro_regular
        ),
        Font(
            resId = R.font.sourcesanspro_semibold,
            weight = FontWeight.SemiBold
        )
    )
)


val typography = Typography(

    h1 = TextStyle(
        fontFamily = fontFamilyMontsserat,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
        letterSpacing = (1.25).sp,
        //  color = red
    ),
    h2 = TextStyle(
        fontFamily = fontFamilySourceSansPro,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        letterSpacing = (0).sp
    ),
    h3 = TextStyle(
        fontFamily = fontFamilySourceSansPro,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        letterSpacing = 0.sp
    ),
    h4 = TextStyle(
        fontFamily = fontFamilyMontsserat,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        letterSpacing = (0.5).sp
    ),
    body1 = TextStyle(
        fontFamily = fontFamilySourceSansPro,
        fontSize = 14.sp,
        letterSpacing = 0.sp
    ),
    button = TextStyle(
        fontFamily = fontFamilySourceSansPro,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        letterSpacing = (1.15).sp
    ),
    caption = TextStyle(
        fontFamily = fontFamilySourceSansPro,
        fontSize = 14.sp,
        fontWeight = FontWeight.SemiBold,
        letterSpacing = (1.15).sp
    ),

    body2 = TextStyle(
        fontFamily = fontFamilySourceSansPro,
        fontSize = 12.sp,
        letterSpacing = 0.sp
    ),

    defaultFontFamily = fontFamilySourceSansPro
)
