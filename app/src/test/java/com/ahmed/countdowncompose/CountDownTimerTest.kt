package com.ahmed.countdowncompose

import kotlinx.coroutines.flow.toList
import org.junit.Assert.assertEquals
import org.junit.Test

import kotlinx.coroutines.test.runTest



class CountDownTimerTest {

    // Test case to verify countdown from 5
    @Test
    fun `test countdown from 5`() = runTest {
        // Given: Creating an instance of CountDownTimer
        val countDownTimer = CountDownTimer()

        // When: Calling the countDown function starting from 5 and collecting the emitted values into a list
        val result = countDownTimer.countDown(5).toList()

        // Then: Verifying that the emitted sequence is [5, 4, 3, 2, 1, 0]
        assertEquals(listOf(5, 4, 3, 2, 1, 0), result)
    }

    // Test case to verify countdown from 3
    @Test
    fun `test countdown from 3`() = runTest {
        // Given: Creating an instance of CountDownTimer
        val countDownTimer = CountDownTimer()

        // When: Calling the countDown function starting from 3 and collecting the emitted values into a list
        val result = countDownTimer.countDown(3).toList()

        // Then: Verifying that the emitted sequence is [3, 2, 1, 0]
        assertEquals(listOf(3, 2, 1, 0), result)
    }

    // Test case to verify countdown from 0
    @Test
    fun `test countdown from 0`() = runTest {
        // Given: Creating an instance of CountDownTimer
        val countDownTimer = CountDownTimer()

        // When: Calling the countDown function starting from 0 and collecting the emitted value into a list
        val result = countDownTimer.countDown(0).toList()

        // Then: Verifying that the emitted sequence is [0]
        assertEquals(listOf(2), result)
    }
}
