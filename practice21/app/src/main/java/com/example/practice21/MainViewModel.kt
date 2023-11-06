package com.example.practice21

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.TreeMap

class MainViewModel: ViewModel() {
    private val isStarted = MutableLiveData(false)
    private var value: MutableLiveData<String>? = null
    private var i: Int = 0

    var milSec = 0
    var sec = 0
    var min = 0
    var timer = ""


    fun getValue(): LiveData<String>? {
        if (value == null) {
            value = MutableLiveData("")
        }
        return value
    }

    fun execute() {
        if (!isStarted.value!!) {
            isStarted.value=true
            isStarted.postValue(true)
            val runnable = Runnable {
                while (isStarted.value == true) {
                    i += 1
                    if (i==100){
                        i=0
                    }
                    milSec = i
                    if (milSec == 99) {

                        milSec = 0
                        sec += 1
                    }

                    if (sec == 60) {
                        sec = 0
                        min += 1
                    }

                    try {
                        timer = String.format("%02d:%02d:%03d", min, sec, milSec)
                        value!!.postValue(timer)
                        Thread.sleep(10)
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                }
            }
            val thread = Thread(runnable)
            thread.start()
        }

    }

        fun stop() {
            if (isStarted.value!!) {

                isStarted.value = false
            }
        }
        fun reset(){
            if (isStarted.value!!) {
                isStarted.value=false
                i=0
                min=0
                milSec=0
                sec=0
                timer=""
        }
}}
