package com.jhughes.eznews.common.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.font.*
import com.jhughes.eznews.R


val Exo2 = FontFamily(
        Font(R.font.exo2_light, FontWeight.Light),
        Font(R.font.exo2_light_italic, FontWeight.Light, FontStyle.Italic),
        Font(R.font.exo2_regular, FontWeight.Normal),
        Font(R.font.exo2_regular_italic, FontWeight.Normal, FontStyle.Italic),
        Font(R.font.exo2_medium, FontWeight.Medium),
        Font(R.font.exo2_medium_italic, FontWeight.Medium, FontStyle.Italic),
        Font(R.font.exo2_semi_bold, FontWeight.SemiBold),
        Font(R.font.exo2_semi_bold_italic, FontWeight.SemiBold, FontStyle.Italic),
        Font(R.font.exo2_bold, FontWeight.Bold),
        Font(R.font.exo2_bold_italic, FontWeight.Bold, FontStyle.Italic),
)

// Set of Material typography styles to start with
val typography = Typography(
        defaultFontFamily = Exo2
//        body1 = TextStyle(
//                fontFamily = FontFamily.Default,
//                fontWeight = FontWeight.Normal,
//                fontSize = 16.sp
//        )
        /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)