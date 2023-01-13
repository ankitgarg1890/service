package com.example.myapplication

import android.app.*
import android.app.PendingIntent.FLAG_IMMUTABLE
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Build.ID
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat

class MyForegroundService:Service() {
    override fun onBind(p0: Intent?): IBinder? {
       return null
    }


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        val intent = Intent(this, MainActivity::class.java)

        val pendingIntent = PendingIntent.getActivity(this, 0, intent, FLAG_IMMUTABLE)

        val notification: Notification = NotificationCompat.Builder(this, "CHANNEL_ID")
            .setContentTitle("Example Service")
            .setContentText("Input")
            .setSmallIcon(R.drawable.ic_baseline_notifications_active_24)

            .setContentIntent(pendingIntent)
            .build()

        startForeground(1, notification)

        Thread {
            while (true) {
                Log.e("Service", "Service is running...")
                try {
                    Thread.sleep(2000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
        }.start()
       // return super.onStartCommand(intent, flags, startId)

        createNotificationChannel1()
        return START_STICKY
    }

    private fun createNotificationChannel1() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(
                "CHANNEL_ID",
                "Example Service Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )

            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(serviceChannel)
        }
    }

    override fun onDestroy() {
       stopForeground(false)
        super.onDestroy()
    }

}