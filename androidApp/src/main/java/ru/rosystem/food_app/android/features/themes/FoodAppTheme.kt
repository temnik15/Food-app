package ru.rosystem.food_app.android.features.themes

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf



@Composable
fun FoodAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalFoodAppColors provides baseColors,
        LocalFoodAppPaddings provides basePaddings,
        LocalFoodAppFontSizes provides baseFontSizes,
        LocalFoodAppRoundedCorners provides baseRoundedCorners,
        content = content
    )
}


object FoodAppTheme {

    val colors: FoodAppColors
        @Composable
        get() = LocalFoodAppColors.current

    val paddings: FoodAppPaddings
        @Composable
        get() = LocalFoodAppPaddings.current

    val fontSizes: FoodAppFontSizes
        @Composable
        get() = LocalFoodAppFontSizes.current

    val roundedCorners: FoodAppRoundedCorners
        @Composable
        get() = LocalFoodAppRoundedCorners.current

    val iconSizes: FoodAppIconSizes
        @Composable
        get() = LocalFoodAppIconSizes.current
}


val LocalFoodAppColors = staticCompositionLocalOf { baseColors }
val LocalFoodAppPaddings = staticCompositionLocalOf { basePaddings }
val LocalFoodAppFontSizes = staticCompositionLocalOf { baseFontSizes }
val LocalFoodAppRoundedCorners = staticCompositionLocalOf { baseRoundedCorners }
val LocalFoodAppIconSizes = staticCompositionLocalOf { baseIconSizes }