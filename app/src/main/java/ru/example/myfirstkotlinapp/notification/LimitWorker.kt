package ru.example.myfirstkotlinapp.notification

import android.content.Context
import android.content.SharedPreferences
import android.os.CountDownTimer
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL


class LimitWorker(val context: Context, workerParameters: WorkerParameters) :
    Worker(context, workerParameters) {

    fun startTimeCounter() {
        var counter= 0
        val timer = object: CountDownTimer(20000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                counter++
                Log.i("CounterWorker",  "$counter")}

            override fun onFinish() {
                Log.i("CounterWorker",  "Finished")}
        }
        timer.start()

//        object : CountDownTimer(50000, 1000) {
//            override fun onTick(millisUntilFinished: Long) {
//                counter++
//                Log.i("Counter",  "$counter")
//            }
//            override fun onFinish() {
//               Log.i("Counter",  "Finished")
//            }
//        }.start()
    }
    val st: String = ""

    fun getipFromaws(): String {
        val whatismyip = URL("https://myexternalip.com/raw")
        val input = BufferedReader(
            InputStreamReader(
                whatismyip.openStream()
            )
        )

        val ip: String = input.readLine()
        Log.i("ip limit worker", ip)//you get the IP as a String
        return ip
    }

    override fun doWork(): Result {

            startTimeCounter()
        getipFromaws()

//        if (getipFromaws() != st) {
//
//            getipFromaws() == st
//
//
//        } else {
//
//
//        }

        return Result.success()
    }
}
