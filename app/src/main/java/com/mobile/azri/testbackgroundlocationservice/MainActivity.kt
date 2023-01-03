package com.mobile.azri.testbackgroundlocationservice

import android.Manifest
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import com.mobile.azri.testbackgroundlocationservice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.TIRAMISU){
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.POST_NOTIFICATIONS,
                    ),
                0
            )
        }else{
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    ),
                0
            )
        }



        Intent(applicationContext,LocationService::class.java).apply {
            action = LocationService.ACTION_START
            startService(this)
            Log.d(TAG, "onCreate: btnStart clicking intent")
        }

        binding?.btnStart?.setOnClickListener {
            Intent(applicationContext,LocationService::class.java).apply {
                action = LocationService.ACTION_START
                startService(this)
                Log.d(TAG, "onCreate: btnStart clicking intent")
            }
        }
        binding?.btnStop?.setOnClickListener {
            Intent(applicationContext,LocationService::class.java).apply {
                action = LocationService.ACTION_STOP
                startService(this)
                Log.d(TAG, "onCreate: btnStop clicking intent")
            }
        }

    }

    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onDestroy() {
        super.onDestroy()
        Intent(applicationContext,LocationService::class.java).apply {
            action = LocationService.ACTION_STOP
            startService(this)
            Log.d(TAG, "onCreate: btnStart clicking intent")
        }
    }
}