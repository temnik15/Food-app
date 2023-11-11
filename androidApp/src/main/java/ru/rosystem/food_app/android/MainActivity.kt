package ru.rosystem.food_app.android

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import ru.rosystem.food_app.android.features.auth.AuthScreen
import ru.rosystem.food_app.android.features.themes.FoodAppTheme


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            FoodAppTheme {
                AuthScreen()
            }
        }
    }
}
