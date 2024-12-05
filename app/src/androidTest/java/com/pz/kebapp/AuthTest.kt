package com.pz.kebapp

import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.pz.kebapp.navigation.Navigation
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class AuthTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setup() {
        composeTestRule.setContent {
            Navigation()
        }
    }

    @Test
    fun testRegisterNav() {
        composeTestRule.onNodeWithText("Konto").performClick()
        composeTestRule.onNode(hasTestTag("Login")).assertExists()

        composeTestRule.onNodeWithText("Zarejestruj się").performClick()
        composeTestRule.onNode(hasTestTag("Register")).assertExists()
    }

    @Test
    fun testLoginNav() {
        composeTestRule.onNodeWithText("Konto").performClick()
        composeTestRule.onNode(hasTestTag("Login")).assertExists()

        composeTestRule.onNodeWithText("Zarejestruj się").performClick()
        composeTestRule.onNode(hasTestTag("Register")).assertExists()

        composeTestRule.onNodeWithText("Zaloguj się").performClick()
        composeTestRule.onNode(hasTestTag("Login")).assertExists()
    }

    @Test
    fun testGuestNav() {
        composeTestRule.onNodeWithText("Ulubione").performClick()
        composeTestRule.onNode(hasTestTag("Guest")).assertExists()

        composeTestRule.onNodeWithText("Zaloguj się").performClick()
        composeTestRule.onNode(hasTestTag("Login")).assertExists()
    }
}