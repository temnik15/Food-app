package ru.rosystem.food_app.android.features.auth.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.rosystem.food_app.android.R
import ru.rosystem.food_app.android.features.themes.FoodAppTheme


/**
 * Поля ввода с статичным заголовком и функционалом скрытия вводимых символов.
 */
@Composable
internal fun TextFieldWithStaticLabel(
    label: String,
    modifier: Modifier = Modifier,
    placeholder: String? = null,
    value: String = "",
    needShowHideMode: Boolean = false,
    onValueChange: (String) -> Unit
) {

    var valueIsHidden by remember {
        mutableStateOf(needShowHideMode)
    }

    Column(modifier = modifier) {
        Text(
            text = label,
            modifier = Modifier.padding(bottom = FoodAppTheme.paddings.xs),
            color = FoodAppTheme.colors.primaryText,
            fontSize = FoodAppTheme.fontSizes.sizeM,
            fontWeight = FontWeight.Medium
        )
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = placeholder?.let {
                {
                    Text(
                        text = it,
                        color = FoodAppTheme.colors.hint,
                        fontSize = FoodAppTheme.fontSizes.sizeM
                    )
                }
            },
            trailingIcon = if (needShowHideMode) {
                {
                    Icon(
                        painter = painterResource(id = if (valueIsHidden) R.drawable.eye_show else R.drawable.eye_hide),
                        contentDescription = "",
                        tint = FoodAppTheme.colors.icon,
                        modifier = Modifier
                            .width(FoodAppTheme.iconSizes.default)
                            .clickable {
                                valueIsHidden = !valueIsHidden
                            }
                    )
                }
            } else null,
            singleLine = true,
            visualTransformation = if (needShowHideMode && valueIsHidden) PasswordVisualTransformation() else VisualTransformation.None,
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = FoodAppTheme.colors.inputFieldBody,
                unfocusedContainerColor = FoodAppTheme.colors.inputFieldBody,
                focusedBorderColor = FoodAppTheme.colors.border,
                unfocusedBorderColor = FoodAppTheme.colors.border
            ),
            shape = RoundedCornerShape(FoodAppTheme.roundedCorners.inputField)
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun TextFieldWithStaticLabelPreview() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
    ) {
        TextFieldWithStaticLabel(
            label = "Поле ввода",
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(12.dp),
            placeholder = "Логин",
            value = ""
        ) {}
    }
}