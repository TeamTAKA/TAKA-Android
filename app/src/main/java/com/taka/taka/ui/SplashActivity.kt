package com.taka.taka.ui

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.taka.taka.R
import kotlin.concurrent.thread

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        thread {
            Thread.sleep(1000)

            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}