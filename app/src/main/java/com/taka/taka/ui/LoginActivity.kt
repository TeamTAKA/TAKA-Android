package com.taka.taka.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.taka.taka.MainActivity
import com.taka.taka.R
import com.taka.taka.databinding.ActivityLoginBinding
import com.taka.taka.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var viewModel: LoginViewModel
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(LoginViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        binding.loginTvSignup.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }

        viewModel.loginSuccess.observe(this) {
            if (it) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }
        viewModel.state.observe(this) { Toast.makeText(this, it, Toast.LENGTH_SHORT).show() }
    }
}