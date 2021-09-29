package com.taka.taka.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.taka.taka.MainActivity
import com.taka.taka.R

class LoginActivity : AppCompatActivity(), View.OnClickListener {
    private val mTvLogin: TextView by lazy { findViewById(R.id.login_tv_login) }
    private val mTvSignup: TextView by lazy { findViewById(R.id.login_tv_signup) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mTvLogin.setOnClickListener(this)
        mTvSignup.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view) {
            mTvLogin -> {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
            mTvSignup -> startActivity(Intent(this, SignupActivity::class.java))
        }
    }
}