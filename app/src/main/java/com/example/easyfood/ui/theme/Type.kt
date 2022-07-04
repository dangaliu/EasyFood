package com.example.easyfood.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
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
    */,
    h1 = TextStyle(
        color = primary,
        fontSize = 25.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = myFont,
    ),
    h2 = TextStyle(
        color = Color.Black,
        fontSize = 20.sp,
        fontFamily = myFont,
        fontWeight = FontWeight.Bold
    ),
    h3 = TextStyle(
        color = darkBlue,
        fontSize = 12.sp,
        fontFamily = myFont,
        fontWeight = FontWeight.Bold
    )
)