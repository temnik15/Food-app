package ru.rosystem.food_app.android.features.themes

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val baseFontSizes = FoodAppFontSizes(sizeS = 12.sp, sizeM = 16.sp, sizeXM = 20.sp, sizeL = 24.sp, sizeXl = 36.sp)

val basePaddings = FoodAppPaddings(
    xs = 4.dp,
    m = 12.dp,
    l = 16.dp
)

// TODO СДЕЛАНО НЕ ПРАВИЛЬНО, НАДО СДЕЛАТЬ ЦВЕТА УРОВНЯ ПРИЛОЖЕНИЯ И УРОВНЯ ЭКРАНА АВТОРИЗАЦИИ
val baseColors = FoodAppColors(
    primaryContentCard = Color(0xFFCADF9A),
    primaryNestedContentCard = Color(0xFF82AC52),
    primaryText = Color(0xFF386641),
    contrastText = Color.White,
    icon = Color(0xFF386641),
    border = Color(0xFF386641),
    hint = Color(0xFF9bb29f),
    inputFieldBody = Color(0xfffdfefd),
    buttonPrimary = Color(0xFF386641),
    buttonSecondary = Color(0xFF6A994E),
    gradientBackground = GradientBackground(
        listOf(
            Color(0xFF386641), Color(0xFF6A994E), Color.White
        )
    )
)

val baseRoundedCorners = FoodAppRoundedCorners(
    card = 8.dp, button = 12.dp, inputField = 8.dp
)

val baseIconSizes = FoodAppIconSizes(24.dp)