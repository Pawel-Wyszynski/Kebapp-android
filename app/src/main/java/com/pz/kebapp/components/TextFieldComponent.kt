package com.pz.kebapp.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.ImeAction
import com.pz.kebapp.ui.theme.TextFieldBackgroundColor
import com.pz.kebapp.ui.theme.TextFieldPrimaryColor
import com.pz.kebapp.ui.theme.nunitoSansFontFamily

@Composable
fun TextFieldComponent(
    labelValue: String,
    icon: ImageVector,
    textValue: MutableState<String>
) {
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
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        singleLine = true,
        maxLines = 1,
        value = textValue.value,
        onValueChange = {
            textValue.value = it
        },
        leadingIcon = {
            Icon(icon, contentDescription = null)
        }
    )
}