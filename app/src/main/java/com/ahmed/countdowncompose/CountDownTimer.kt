package com.ahmed.countdowncompose

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CountDownTimer {
    fun countDown(from: Int): Flow<Int>  = flow {
        for(i in from downTo 0) {
            delay(1000L)
            emit(i)
        }
    }
}