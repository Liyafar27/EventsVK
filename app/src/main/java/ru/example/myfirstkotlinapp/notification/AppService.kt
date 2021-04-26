//package ru.example.myfirstkotlinapp.notification
//
//import android.app.*
//import android.content.Intent
//import android.os.Build
//import android.util.Log
//import androidx.core.app.NotificationCompat
//import kotlinx.coroutines.runBlocking
//import ru.example.myfirstkotlinapp.R
//import ru.example.myfirstkotlinapp.data.storage.Storage
//import ru.example.myfirstkotlinapp.screens.main.MainActivity
//
//
//class AppService : IntentService("AppService Kotlin") {
//    private val CHANNEL_ID = "AppService Kotlin"
//
//    override fun onHandleIntent(intent: Intent?) {
//
//        var message = ""
//        val storage = Storage.instance
//
//        try {
//
//            runBlocking {
//
//                val listbefore = storage.getLocalRepo()
//                val list = storage.getChangedRepositories()
//
//                    for (i in list.indices) {
//                        val nameLocal = list[i].name
//                        val stargazersCountLocal = list[i].stargazers_count
//                        val stargazersCountBefore = listbefore[i].stargazers_count
//
//                        val newStars =
//                            -1 * (stargazersCountBefore.toInt() - stargazersCountLocal.toInt())
//                        message += "$nameLocal  : " +
//                                " + $newStars  new stars " +
//                                ", total number: $stargazersCountLocal  stars\n"
//                    }
//                        if (message.isNotEmpty()){
//                createNotification(message)}}
//
//
//        } catch (exception: Exception) {
//            exception.printStackTrace()
//
//            Log.i("exception", "An exception occurred : $exception")
//        }
//    }
//
//    private fun createNotification(message: String) {
//
//        val notificationIntent = Intent(this, MainActivity::class.java)
//        val pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0)
//
//        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
//            .setContentTitle("Stargazers Count")
//            .setContentText(message)
//            .setSmallIcon(R.drawable.ic_stat_new_message)
//            .setContentIntent(pendingIntent)
//            .setStyle(NotificationCompat.BigTextStyle().bigText(message))
//            .build()
//        startForeground(34, notification)
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            val importance = NotificationManager.IMPORTANCE_DEFAULT
//            val channel =
//                NotificationChannel(CHANNEL_ID, "channel", importance)
//            channel.description = "my channel"
//            val notificationManager = getSystemService(
//                NotificationManager::class.java
//            )
//            notificationManager?.createNotificationChannel(channel)
//
//            val manager = getSystemService(NotificationManager::class.java)
//            manager!!.notify(44, notification)
//        }
//
//    }
//
//    }
//
