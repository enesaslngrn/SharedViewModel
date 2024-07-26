package com.example.sharedviewmodel

import android.os.CountDownTimer
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TestSharedViewModel : ViewModel() {

    var language = MutableLiveData("Kotlin")

    fun passLanguageData(newLanguage : String){
        language.value = newLanguage
    }

    // Timer
    private lateinit var timer : CountDownTimer
    val seconds = MutableLiveData<Int>()
    var isCountRunning = false
    val isCountFinished = MutableLiveData<Boolean>()

    fun startTimer(){
        timer = object : CountDownTimer(20000,1000){
            override fun onTick(millisUntilFinished: Long) {
                val timeLeft = millisUntilFinished / 1000
                seconds.value = timeLeft.toInt()
                isCountRunning = true
            }

            override fun onFinish() {
                isCountFinished.value = true
                isCountRunning = false
                stopTimer()
            }
        }.start()
    }

    fun stopTimer(){
        timer.cancel()
    }
}