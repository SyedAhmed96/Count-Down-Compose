package com.ahmed.countdowncompose

import androidx.compose.ui.test.assert
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test
import androidx.compose.ui.test.performClick
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class CountDownTimerTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testTimerCountdown() {
        // Launch your Composable
        composeTestRule.setContent {
            CountDownTimerScreen(viewModel = CountDownViewModel()) // Your ViewModel and Composable
        }

        // Step 1: Assert initial timer value is 10
        composeTestRule
            .onNodeWithTag("timerValueText")
            .assertTextEquals("10")

        // Step 2: Click the start button to begin countdown
        composeTestRule
            .onNodeWithTag("startButton")  // Match by test tag
            .performClick()

        // Step 3: Use advanceTimeBy to simulate 11 seconds passing (for the countdown to complete)
        composeTestRule.mainClock.advanceTimeBy(11000) // Advances the virtual time by 11 seconds

        // Step 4: Wait for UI updates to complete
        Thread.sleep(11000)
        // composeTestRule.awaitIdle()

        // Step 5: Assert that the timer value is now 0 after the countdown
        composeTestRule
            .onNodeWithTag("timerValueText")
            .assertTextEquals("0")
    }

    @Test
    fun testTimerPause() {
        // Launch your Composable
        composeTestRule.setContent {
            CountDownTimerScreen(viewModel = CountDownViewModel()) // Your ViewModel and Composable
        }

        // Step 1: Assert initial timer value is 10
        composeTestRule
            .onNodeWithTag("timerValueText")
            .assertTextEquals("10")

        // Step 2: Click the start button to begin countdown
        composeTestRule
            .onNodeWithTag("startButton")  // Match by test tag
            .performClick()

        // Step 3: Use advanceTimeBy to simulate 5 seconds passing (half of the countdown)
        composeTestRule.mainClock.advanceTimeBy(5000) // Advances the virtual time by 5 seconds
        Thread.sleep(6000)

        // Step 4: Assert that the timer value is 5 after 5 seconds
        composeTestRule
            .onNodeWithTag("timerValueText")
            .assertTextEquals("5")

        // Step 5: Click the pause button to stop the timer
        composeTestRule
            .onNodeWithTag("pauseButton") // Match pause button by test tag
            .performClick()

        // Step 6: Assert that the timer value remains at 5 (since it's paused)
        composeTestRule
            .onNodeWithTag("timerValueText")
            .assertTextEquals("5")
    }

    @Test
    fun testTimerResume() {
        // Launch your Composable
        composeTestRule.setContent {
            CountDownTimerScreen(viewModel = CountDownViewModel()) // Your ViewModel and Composable
        }

        // Step 1: Assert initial timer value is 10
        composeTestRule
            .onNodeWithTag("timerValueText")
            .assertTextEquals("10")

        // Step 2: Click the start button to begin countdown
        composeTestRule
            .onNodeWithTag("startButton")  // Match by test tag
            .performClick()

        // Step 3: Use advanceTimeBy to simulate 5 seconds passing
        composeTestRule.mainClock.advanceTimeBy(6000) // Advances the virtual time by 5 seconds
        Thread.sleep(6000)

        // Step 4: Assert that the timer value is 5 after 5 seconds
        composeTestRule
            .onNodeWithTag("timerValueText")
            .assertTextEquals("5")

        // Step 5: Click the pause button to stop the timer
        composeTestRule
            .onNodeWithTag("pauseButton") // Match pause button by test tag
            .performClick()

        // Step 6: Assert that the timer value remains at 5 (paused)
        composeTestRule
            .onNodeWithTag("timerValueText")
            .assertTextEquals("5")

        // Step 7: Click the resume button to continue the countdown
        composeTestRule
            .onNodeWithTag("resumeButton") // Match resume button by test tag
            .performClick()

        // Step 8: Use advanceTimeBy to simulate another 5 seconds passing
        composeTestRule.mainClock.advanceTimeBy(6000) // Advances the virtual time by 5 more seconds
        Thread.sleep(6000)

        // Step 9: Assert that the timer value is now 0 after resuming and completing the countdown
        composeTestRule
            .onNodeWithTag("timerValueText")
            .assertTextEquals("0")
    }

    @Test
    fun testTimerReset() {
        // Launch your Composable
        composeTestRule.setContent {
            CountDownTimerScreen(viewModel = CountDownViewModel()) // Your ViewModel and Composable
        }

        // Step 1: Assert initial timer value is 10
        composeTestRule
            .onNodeWithTag("timerValueText")
            .assertTextEquals("10")

        // Step 2: Click the start button to begin countdown
        composeTestRule
            .onNodeWithTag("startButton")  // Match by test tag
            .performClick()

        // Step 3: Use advanceTimeBy to simulate 5 seconds passing
        composeTestRule.mainClock.advanceTimeBy(6000) // Advances the virtual time by 5 seconds
        Thread.sleep(6000)

        // Step 4: Assert that the timer value is 5 after 5 seconds
        composeTestRule
            .onNodeWithTag("timerValueText")
            .assertTextEquals("5")

        // Step 5: Click the reset button to reset the timer
        composeTestRule
            .onNodeWithTag("resetButton") // Match reset button by test tag
            .performClick()

        // Step 6: Assert that the timer value is reset to the initial value (10)
        composeTestRule
            .onNodeWithTag("timerValueText")
            .assertTextEquals("10")
    }
}
