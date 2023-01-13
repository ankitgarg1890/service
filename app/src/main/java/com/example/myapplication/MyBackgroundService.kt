package com.example.myapplication

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import android.widget.Toast

class MyBackgroundService:Service() {
    override fun onBind(p0: Intent?): IBinder? {
        return null
    }



    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        Toast.makeText(this,"Service start",Toast.LENGTH_SHORT).show()

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



        return super.onStartCommand(intent, flags, startId)



    }


    override fun onDestroy() {
        super.onDestroy()
    }
}