package com.example.myapplication

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startForegroundService

class MainActivity : AppCompatActivity() {

    lateinit var startbutton: Button
    lateinit var stopbutton: Button
    private lateinit var serviceIntent:Intent

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startbutton = findViewById(R.id.bt_start)
        stopbutton = findViewById(R.id.bt_stop)

        startbutton.setOnClickListener {
            //val serviceIntent = Intent(this, MyBackgroundService::class.java)
           //startService(serviceIntent)
            serviceIntent = Intent(this, MyForegroundService::class.java)


         startForegroundService(serviceIntent)


        }


           stopbutton.setOnClickListener {

               val serviceIntent = Intent(this, MyForegroundService::class.java)
               stopService(serviceIntent)


           }

    }

    override fun onPause() {
        super.onPause()
      //stopService(serviceIntent)
    }
}