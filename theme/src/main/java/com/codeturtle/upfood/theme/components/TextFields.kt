package com.codeturtle.upfood.theme.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.codeturtle.upfood.theme.R
import com.codeturtle.upfood.theme.UpFoodTheme
import com.codeturtle.upfood.theme.utils.UiText

@Composable
fun AppTextField(
    value: String,
    onValueChange: (String) -> Unit,
    @StringRes label: Int,
    error: UiText? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Done,
    onDone: () -> Unit = {},
) {
    val focusManager = LocalFocusManager.current
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    Box {
        OutlinedTextField(
            modifier = Modifier
                .testTag(stringResource(label))
                .fillMaxWidth(),
            value = value,
            shape = RoundedCornerShape(12.dp),
            onValueChange = { onValueChange(it) },
            label = {
                Text(stringResource(label))
            },
            singleLine = true,
            isError = error != null,
            supportingText = {
                error?.asString()?.let {
                    Text(
                        text = it, color = MaterialTheme.colorScheme.error
                    )
                }
            },
            trailingIcon = {
                if (keyboardType == KeyboardType.Password) {
                    val image = when {
                        passwordVisible -> Icons.Filled.Visibility
                        else -> Icons.Filled.VisibilityOff
                    }
                    val description = when {
                        passwordVisible -> stringResource(R.string.hide_password)
                        else -> stringResource(R.string.show_password)
                    }
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(imageVector = image, description)
                    }
                }
            },
            visualTransformation = when (keyboardType) {
                KeyboardType.Password -> when {
                    !passwordVisible -> PasswordVisualTransformation()
                    else -> VisualTransformation.None
                }

                else -> VisualTransformation.None
            },
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                    onDone()
                },
                onNext = { focusManager.moveFocus(FocusDirection.Down) },
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType,
                imeAction = imeAction
            )
        )
    }
}

@UpFoodPreview
@Composable
private fun AppTextFieldPreview1() {
    UpFoodTheme {
        Surface {
            AppTextField(
                value = "",
                onValueChange = {},
                label = R.string.password,
                error = UiText.StringResource(R.string.password_error),
                keyboardType = KeyboardType.Password
            )
        }
    }
}

@UpFoodPreview
@Composable
private fun AppTextFieldPreview2() {
    UpFoodTheme {
        Surface {
            AppTextField(
                value = "",
                onValueChange = {},
                label = R.string.email,
//                error = UiText.StringResource(R.string.password_error),
                keyboardType = KeyboardType.Email
            )
        }
    }
}