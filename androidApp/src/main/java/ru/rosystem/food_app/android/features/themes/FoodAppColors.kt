package ru.rosystem.food_app.android.features.themes

import androidx.compose.ui.graphics.Color

data class FoodAppColors(
    val primaryContentCard: Color,
    val primaryNestedContentCard: Color,
    val primaryText: Color,
    val contrastText: Color,
    val icon: Color,
    val border: Color,
    val hint: Color,
    val inputFieldBody: Color,
    val buttonPrimary: Color,
    val buttonSecondary: Color,
    val gradientBackground: GradientBackground
)

data class GradientBackground(
    val colors: List<Color>
)