package ru.example.myfirstkotlinapp.notification

import android.content.Context
import android.util.Log


class Worker( val context: Context, workerParameters: WorkerParameters) : Worker(context,workerParameters) {
    fun doWork(): Result {
        Log.d(TAG, "doWork: start")
        try {

        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        Log.d(TAG, "doWork: end")
        return Result.success()
    }

    companion object {
        const val TAG = "workmng"
    }
}