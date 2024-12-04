package com.ahmed.countdowncompose

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class CountDownViewModel: ViewModel() {
    // Mutable state flow for the countdown timer value (default set to 10 seconds)
    private val _timerState = MutableStateFlow(10) // default timer value
    val timerState: StateFlow<Int> = _timerState // Exposes the timer value as an immutable state flow

    // Mutable state flow to track whether the timer is running
    private val _isRunning = MutableStateFlow(false)
    val isRunning: StateFlow<Boolean> = _isRunning // Exposes the running state as an immutable state flow

    // Coroutine scope for managing the countdown timer job
    var countDownJob: CoroutineScope? = null

    /**
     * Starts the countdown timer from the given time.
     * If the timer is already running, this function does nothing.
     *
     * @param from The starting value for the countdown timer.
     */
    fun startTimer(from: Int) {
        // Prevent starting a new timer if one is already running
        if (_isRunning.value) return

        _isRunning.value = true

        // Create a coroutine scope for running the countdown timer
        countDownJob = CoroutineScope(Dispatchers.IO)

        countDownJob?.launch {
            // Use a helper class to emit countdown values
            CountDownTimer().countDown(from = from).collectLatest { time ->
                _timerState.value = time // Update the timer state with the latest value
            }

            // Reset the running state once the timer completes
            _isRunning.value = false
        }
    }

    /**
     * Pauses the countdown timer by canceling the running job.
     * Updates the running state to false.
     */
    fun pauseTimer() {
        _isRunning.value = false
        countDownJob?.cancel() // Cancel the active countdown job
    }

    /**
     * Resets the timer to its default value (10 seconds).
     * Also pauses the timer if it is running.
     */
    fun resetTimer() {
        pauseTimer() // Ensure the timer is paused before resetting
        _timerState.value = 10 // Reset the timer value
    }

    /**
     * Cleans up resources when the ViewModel is destroyed.
     * Cancels the countdown job to avoid memory leaks.
     */
    public override fun onCleared() {
        super.onCleared()

        countDownJob?.cancel() // Cancel the countdown job if it is still active
    }
}
