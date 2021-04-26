//package ru.example.myfirstkotlinapp.notification
//
//import android.app.AlarmManager
//import android.app.NotificationChannel
//import android.app.NotificationManager
//import android.app.PendingIntent
//import android.content.BroadcastReceiver
//import android.content.Context
//import android.content.Intent
//import android.os.Build
//import android.util.Log
//import androidx.core.app.NotificationCompat
//import ru.example.myfirstkotlinapp.R
//import ru.example.myfirstkotlinapp.screens.main.MainActivity
//
//class BootReceiver : BroadcastReceiver() {
//
//    override fun onReceive(context: Context, intent: Intent) {
//        Log.i("boot","!!!boot")
//
//            val intent1 = Intent(context,   AlarmReceiver::class.java)
//            val pendingIntent = PendingIntent.getBroadcast(context, 0, intent1, 0)
//            val manager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
//            manager.setRepeating(AlarmManager.ELAPSED_REALTIME,5000, 60000,
//                pendingIntent
//            )
//            val CHANNEL_ID = "AppService Kotlin"
//
//                val notificationIntent = Intent(context, MainActivity::class.java)
//                val pendingIntent2 = PendingIntent.getActivity(context, 0, notificationIntent, 0)
//
//                val notification = NotificationCompat.Builder(context, CHANNEL_ID)
//                    .setContentTitle("Boot")
//                    .setContentText("boot")
//                    .setSmallIcon(R.drawable.ic_stat_new_message)
//                    .setContentIntent(pendingIntent)
//                    .setStyle(NotificationCompat.BigTextStyle().bigText("boot"))
//                    .build()
//
//
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                    val importance = NotificationManager.IMPORTANCE_DEFAULT
//                    val channel =
//                        NotificationChannel(CHANNEL_ID, "channel", importance)
//                    channel.description = "my channel"
//                    val notificationManager = context.getSystemService(
//                        NotificationManager::class.java
//                    )
//                    notificationManager?.createNotificationChannel(channel)
//
//                    val manager1 = context.getSystemService(NotificationManager::class.java)
//                    manager1!!.notify(44, notification)
//                }
//
//            }
////    }
//}
//
