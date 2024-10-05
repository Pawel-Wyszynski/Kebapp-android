package com.pz.kebapp.screens.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pz.kebapp.R
import com.pz.kebapp.components.ButtonComponent
import com.pz.kebapp.components.ClickableTextComponent
import com.pz.kebapp.components.DividerTextComponent
import com.pz.kebapp.components.HeadingTextComponent
import com.pz.kebapp.components.ImageComponent
import com.pz.kebapp.components.PasswordTextFieldComponent
import com.pz.kebapp.components.TextFieldComponent
import com.pz.kebapp.ui.theme.Background

@Composable
fun SignUpScreen() {
    val emailState = remember { mutableStateOf("") }
    val nameState = remember { mutableStateOf("") }
    val passwordState = remember { mutableStateOf("") }
    val confirmPasswordState = remember { mutableStateOf("") }
    val context = LocalContext.current

    Surface(
        color = Background,
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
            .padding(28.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            ImageComponent(
                painterResource = painterResource(id = R.drawable.kebapp_logo)
            )

            Spacer(modifier = Modifier.height(20.dp))

            HeadingTextComponent(value = "Zarejestruj się")

            Spacer(modifier = Modifier.height(20.dp))

            TextFieldComponent(
                labelValue = "Email",
                icon = Icons.Default.Email,
                textValue = emailState
            )

            TextFieldComponent(
                labelValue = "Nazwa użytkownika",
                icon = Icons.Default.Person,
                textValue = nameState
            )

            PasswordTextFieldComponent(
                labelValue = "Hasło",
                icon = Icons.Default.Lock,
                passwordValue = passwordState
            )

            PasswordTextFieldComponent(
                labelValue = "Powtórz hasło",
                icon = Icons.Default.Lock,
                passwordValue = confirmPasswordState
            )

            Spacer(modifier = Modifier.height(40.dp))

            ButtonComponent(value = "Zarejestruj", onSelect = {

            })

            Spacer(modifier = Modifier.height(20.dp))

            DividerTextComponent(value = "lub")

            Spacer(modifier = Modifier.height(20.dp))

            ClickableTextComponent(
                valueInitial = "Masz już konto? ",
                valueAnnotated = "Zaloguj się",
                onTextSelected = {

                })
        }
    }
}

@Preview
@Composable
fun SignUpScreenPreview() {
    SignUpScreen()
}