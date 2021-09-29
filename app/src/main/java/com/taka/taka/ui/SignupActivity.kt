package com.taka.taka.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.taka.taka.MainActivity
import com.taka.taka.R

class SignupActivity : AppCompatActivity(), View.OnClickListener {
    val mTvSignup: TextView by lazy { findViewById(R.id.signup_tv_signup) }
    val mTvLogin: TextView by lazy { findViewById(R.id.signup_tv_login) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        mTvSignup.setOnClickListener(this)
        mTvLogin.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view) {
            mTvSignup -> {
                val intent = Intent(this, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                finish()
            }
            mTvLogin -> finish()
        }
    }
}