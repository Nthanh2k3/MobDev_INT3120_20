package com.example.lec11

import android.app.Service
import android.content.Intent
import android.os.CountDownTimer
import android.os.IBinder

class CountDownService : Service()  {

    private var timer: CountDownTimer? = null
    private val binder = CountDownBinder()
    private var remainSec = 60

    override fun onBind(p0: Intent?): IBinder? {
        TODO("Not yet implemented")
        return null
    }

    inner class CountDownBinder() {
        fun getService() {
            this@CountDownService
        }
    }

    fun startCountdown(callback: (Int) -> Unit) {
        timer = object : CountDownTimer((remainSec * 1000).toLong(), 1000) {
            override fun onTick(millisUntilFinished: Long) {
                remainSec = (millisUntilFinished / 1000).toInt()
                callback(remainSec)
            }

            override fun onFinish() {
                remainSec = 0
                callback(remainSec)
            }
        }.start()
    }

    fun stopCountdown() {
        timer?.cancel()
        timer = null
    }



}