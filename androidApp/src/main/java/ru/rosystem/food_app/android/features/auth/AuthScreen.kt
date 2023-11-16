package ru.rosystem.food_app.android.features.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.arkivanov.mvikotlin.extensions.coroutines.states
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import ru.rosystem.food_app.android.R
import ru.rosystem.food_app.android.features.auth.di.DaggerAuthComponent
import ru.rosystem.food_app.android.features.auth.store.Intent
import ru.rosystem.food_app.android.features.auth.ui.TextFieldWithStaticLabel
import ru.rosystem.food_app.android.features.mvi_extensions.provideStore
import ru.rosystem.food_app.android.features.themes.FoodAppTheme


/**
 * Экран авторизации.
 *
 * @author ra.temnikov
 */
@Composable
internal fun AuthScreen() {
    val store = provideStore {
        DaggerAuthComponent
            .factory()
            .create()
            .storeFactory
            .create(it)
    }

    val state by store.states.collectAsState(store.state)

    AuthScreenContent(
        login = state.login,
        loginChange = { store.accept(Intent.ChangeLogin(it)) },
        password = state.password,
        passwordChange = { store.accept(Intent.ChangePassword(it)) }
    )
}

@Composable
private fun AuthScreenContent(
    login: String,
    loginChange: (String) -> Unit,
    password: String,
    passwordChange: (String) -> Unit
) {
    val statusBarTopPadding = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()
    val navigationBottomPaddings =
        WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()
    val status = rememberSystemUiController()
    status.setNavigationBarColor(Color.Transparent, darkIcons = true)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(FoodAppTheme.colors.gradientBackground.colors)
            )
            .padding(
                top = statusBarTopPadding,
                bottom = navigationBottomPaddings
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(FoodAppTheme.paddings.l)
                .verticalScroll(rememberScrollState())
                .align(Alignment.Center)
        ) {

            Text(
                modifier = Modifier
                    .align(CenterHorizontally)
                    .padding(FoodAppTheme.paddings.l),
                text = stringResource(id = R.string.auth_card_title),
                fontSize = FoodAppTheme.fontSizes.sizeXl,
                color = FoodAppTheme.colors.contrastText
            )
            Column(
                modifier = Modifier.background(
                    FoodAppTheme.colors.primaryContentCard,
                    RoundedCornerShape(FoodAppTheme.roundedCorners.card)
                )
            ) {
                TextFieldWithStaticLabel(
                    modifier = Modifier.padding(FoodAppTheme.paddings.m),
                    label = stringResource(id = R.string.auth_login_input_label),
                    value = login,
                    onValueChange = loginChange,
                    placeholder = stringResource(id = R.string.auth_login_input_hint)
                )
                TextFieldWithStaticLabel(
                    modifier = Modifier.padding(FoodAppTheme.paddings.m),
                    label = stringResource(id = R.string.auth_password_input_label),
                    value = password,
                    onValueChange = passwordChange,
                    needShowHideMode = true,
                    placeholder = stringResource(id = R.string.auth_password_input_hint)
                )
            }
            Text(
                modifier = Modifier
                    .align(CenterHorizontally)
                    .padding(FoodAppTheme.paddings.l),
                text = stringResource(id = R.string.auth_ask_forgot_password),
                fontSize = FoodAppTheme.fontSizes.sizeM,
                fontWeight = FontWeight.Bold,
                color = FoodAppTheme.colors.primaryText
            )

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(containerColor = FoodAppTheme.colors.buttonPrimary)
            ) {
                Text(
                    text = stringResource(id = R.string.auth_button_login),
                    fontSize = FoodAppTheme.fontSizes.sizeXM,
                    modifier = Modifier.padding(vertical = FoodAppTheme.paddings.xs)
                )
            }

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = FoodAppTheme.paddings.m),
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(containerColor = FoodAppTheme.colors.buttonSecondary)
            ) {
                Text(text = stringResource(id = R.string.auth_button_registration), fontSize = FoodAppTheme.fontSizes.sizeM)
            }

            Text(
                modifier = Modifier
                    .align(CenterHorizontally)
                    .padding(FoodAppTheme.paddings.l),
                text = stringResource(id = R.string.auth_continue_without_authorization),
                fontSize = FoodAppTheme.fontSizes.sizeM,
                fontWeight = FontWeight.Bold,
                color = FoodAppTheme.colors.primaryText
            )
        }
    }
}


@Preview(showSystemUi = true)
@Composable
private fun AuthScreenHostPreview() {
    AuthScreenContent(
        "234,",
        {},
        "345",
        {}
    )
}