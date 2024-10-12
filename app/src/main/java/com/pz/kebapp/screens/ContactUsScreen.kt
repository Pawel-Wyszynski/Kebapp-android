package com.pz.kebapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Description
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.pz.kebapp.R
import com.pz.kebapp.components.ButtonComponent
import com.pz.kebapp.components.HeadingTextComponent
import com.pz.kebapp.components.ImageComponent
import com.pz.kebapp.components.LongTextFieldComponent
import com.pz.kebapp.functions.userFunctions.sendMessageFunction
import com.pz.kebapp.navigation.BottomNavigationBar
import com.pz.kebapp.ui.theme.Background

@Composable
fun ContactUsScreen(
    navController: NavHostController
) {
    val messageState = remember { mutableStateOf("") }
    val context = LocalContext.current
    val scrollState = rememberScrollState()

    Scaffold(
        bottomBar = {
            BottomNavigationBar(screen = "Kontakt", navController)
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Background)
            ) {
                Surface(
                    color = Background,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Background)
                        .padding(28.dp, 15.dp, 28.dp, paddingValues.calculateBottomPadding())
                        .verticalScroll(scrollState)
                ) {
                    Column(modifier = Modifier.fillMaxSize()) {
                        ImageComponent(
                            painterResource = painterResource(id = R.drawable.brodacz)
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        HeadingTextComponent(value = "Jakieś sugestie?\nNapisz do nas")

                        Spacer(modifier = Modifier.height(20.dp))

                        LongTextFieldComponent(
                            labelValue = "Wiadomość",
                            icon = Icons.Default.Description,
                            textValue = messageState
                        )

                        Spacer(modifier = Modifier.height(40.dp))

                        ButtonComponent(value = "Wyślij", onSelect = {
                            sendMessageFunction(
                                messageState.value,
                                context,
                                navController
                            )
                        })
                    }
                }
            }
        }
    )
}

@Preview
@Composable
fun ContactUsScreenPreview() {
    ContactUsScreen(rememberNavController())
}