package com.taka.taka.presentation.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.taka.taka.presentation.MainActivity
import com.taka.taka.R
import com.taka.taka.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity() {

    private lateinit var viewModel: SignupViewModel
    private lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(SignupViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_signup)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        binding.signupTvLogin.setOnClickListener { finish() }

        viewModel.signupSuccess.observe(this) {
            if (it) {
                val intent = Intent(this, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                finish()
            }
        }

        viewModel.state.observe(this) { Toast.makeText(this, it, Toast.LENGTH_SHORT).show() }
    }
}