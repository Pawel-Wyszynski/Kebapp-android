package com.pz.kebapp.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.pz.kebapp.ui.theme.TextFieldBackgroundColor
import com.pz.kebapp.ui.theme.TextFieldPrimaryColor
import com.pz.kebapp.ui.theme.nunitoSansFontFamily

@Composable
fun PasswordTextFieldComponent(
    labelValue: String,
    icon: ImageVector,
    passwordValue: MutableState<String>
) {
    val passwordVisible = remember {
        mutableStateOf(false)
    }
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = labelValue, fontFamily = nunitoSansFontFamily) },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = TextFieldPrimaryColor,
            focusedLabelColor = TextFieldPrimaryColor,
            cursorColor = TextFieldPrimaryColor,
            focusedContainerColor = TextFieldBackgroundColor,
            unfocusedContainerColor = TextFieldBackgroundColor
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Next
        ),
        singleLine = true,
        maxLines = 1,
        value = passwordValue.value,
        onValueChange = {
            passwordValue.value = it
        },
        leadingIcon = {
            Icon(imageVector = icon, contentDescription = null)
        },
        trailingIcon = {
            val iconImage = if (passwordVisible.value) {
                Icons.Filled.Visibility
            } else {
                Icons.Filled.VisibilityOff
            }
            val description = if (passwordVisible.value) {
                "Ukryj hasło"
            } else {
                "Pokaż hasło"
            }
            IconButton(onClick = { passwordVisible.value = !passwordVisible.value }) {
                Icon(imageVector = iconImage, contentDescription = description)
            }
        },
        visualTransformation = if (passwordVisible.value)
            VisualTransformation.None else PasswordVisualTransformation()
    )
}