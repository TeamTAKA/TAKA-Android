package com.taka.taka.presentation.splash

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.taka.taka.R
import com.taka.taka.presentation.MainActivity
import com.taka.taka.presentation.login.LoginActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlin.concurrent.thread

@AndroidEntryPoint
@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    private val viewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        thread {
            Thread.sleep(1000)
        }

        if (viewModel.isAutoLoginEnabled) {
            startActivity(Intent(this, MainActivity::class.java))
        } else {
            startActivity(Intent(this, LoginActivity::class.java))
        }
        finish()
    }
}