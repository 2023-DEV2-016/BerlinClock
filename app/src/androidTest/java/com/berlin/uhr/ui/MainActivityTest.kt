package com.berlin.uhr.ui

import androidx.activity.compose.setContent
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.berlin.uhr.ui.theme.BerlinClockTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {
        composeTestRule.activity.setContent {
            BerlinClockTheme {
                BerlinClockScreen()
            }
        }
    }

    @Test
    fun shouldDisplayFullTime() {
        composeTestRule.onNodeWithTag("normalTime").assertIsDisplayed()
        composeTestRule.onNodeWithTag("secondsLamp").assertIsDisplayed()
        composeTestRule.onNodeWithTag("fiveHourLamp1").assertIsDisplayed()
        composeTestRule.onNodeWithTag("oneHourLamp1").assertIsDisplayed()
        composeTestRule.onNodeWithTag("fiveMinutes1").assertIsDisplayed()
        composeTestRule.onNodeWithTag("oneMinute1").assertIsDisplayed()
    }
}
