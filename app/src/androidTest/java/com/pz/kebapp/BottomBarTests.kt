package com.pz.kebapp

import android.content.Context
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.platform.app.InstrumentationRegistry
import com.pz.kebapp.data.SessionManager
import com.pz.kebapp.navigation.Navigation
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class BottomBarTests {

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var sessionManager: SessionManager

    @Before
    fun setup() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        sessionManager = SessionManager(context)

        context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)
            .edit()
            .clear()
            .apply()
    }

    @Test
    fun testHomeButton() {
        composeTestRule.setContent {
            Navigation()
        }

        composeTestRule.onNodeWithText("Home").performClick()

        composeTestRule.onNode(hasTestTag("Mapa")).assertExists()
    }

    @Test
    fun testListButton() {
        composeTestRule.setContent {
            Navigation()
        }

        composeTestRule.onNodeWithText("Kebaby").performClick()

        composeTestRule.onNode(hasTestTag("Lista")).assertExists()
    }

    @Test
    fun testFavoritesButtonNotLoggedIn() {
        composeTestRule.setContent {
            Navigation()
        }

        composeTestRule.onNodeWithText("Ulubione").performClick()

        composeTestRule.onNode(hasTestTag("Guest")).assertExists()
    }

    @Test
    fun testContactUsButtonNotLoggedIn() {
        composeTestRule.setContent {
            Navigation()
        }

        composeTestRule.onNodeWithText("Kontakt").performClick()

        composeTestRule.onNode(hasTestTag("Guest")).assertExists()
    }

    @Test
    fun testFavoritesButton() {
        sessionManager.saveAuthToken("token")

        composeTestRule.setContent {
            Navigation()
        }

        composeTestRule.onNodeWithText("Ulubione").performClick()

        composeTestRule.onNode(hasTestTag("Ulubione")).assertExists()
    }

    @Test
    fun testContactUsButton() {
        sessionManager.saveAuthToken("token")

        composeTestRule.setContent {
            Navigation()
        }

        composeTestRule.onNodeWithText("Kontakt").performClick()

        composeTestRule.onNode(hasTestTag("Kontakt")).assertExists()
    }

    @Test
    fun testLoginButton() {
        composeTestRule.setContent {
            Navigation()
        }

        composeTestRule.onNodeWithText("Konto").performClick()

        composeTestRule.onNode(hasTestTag("Login")).assertExists()
    }
}
