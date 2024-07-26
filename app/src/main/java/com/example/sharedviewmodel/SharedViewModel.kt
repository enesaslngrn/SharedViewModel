package com.example.sharedviewmodel

import android.app.Application
import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class SharedViewModel(application: Application) : AndroidViewModel(application){

    val TAG = "SharedViewModel"
    init {
        Log.d(TAG,"SharedViewModel actived")
    }
    var number = 0
    fun addNumber(){
        number++
    }
    var backgroundColor : Int = R.color.white
    fun changeBackgroundColor(){
        backgroundColor = R.color.red
    }
    override fun onCleared() {
        super.onCleared()
        Log.d(TAG,"onCleared -> SharedViewModel deactivated")
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