package com.levento.sfrapp.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.levento.sfrapp.R


private val fontFamilyMontsserat = FontFamily(
    listOf(
        Font(resId = R.font.montserrat_regular),
        Font(R.font.montserrat_medium, FontWeight.W500),
        Font(R.font.montserrat_semibold, FontWeight.W600),
        Font(R.font.montserrat_bold, FontWeight.Bold)
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
        fontWeight = FontWeight.W600,
        fontSize = 18.sp,
        letterSpacing = (1.25).sp,
        color = red
    ),
    h2 = TextStyle(
        fontFamily = fontFamilySourceSansPro,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        letterSpacing = (0).sp
    ),
    h3 = TextStyle(
        fontFamily = fontFamilySourceSansPro,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        letterSpacing = 0.sp
    ),
    h4 = TextStyle(
        fontFamily = fontFamilySourceSansPro,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
        color = gray,
       // letterSpacing = (1).sp
    ),
    h5 = TextStyle(
        fontFamily = fontFamilySourceSansPro,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        color = Color.DarkGray,
        // letterSpacing = (1).sp
    ),
    h6 = TextStyle(
        fontFamily = fontFamilyMontsserat,
        fontWeight = FontWeight.W600,
        fontSize = 16.sp
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

    subtitle2 = TextStyle(
        fontFamily = fontFamilyMontsserat,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),



    defaultFontFamily = fontFamilySourceSansPro
)
