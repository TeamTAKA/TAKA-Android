package com.taka.taka.presentation.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.taka.taka.R
import com.taka.taka.databinding.ActivityLoginBinding
import com.taka.taka.presentation.MainActivity
import com.taka.taka.presentation.signup.SignupActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private val viewModel: LoginViewModel by viewModels()
    private val binding by lazy { ActivityLoginBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        window.statusBarColor = getColor(R.color.gray_light)

        initBinding()
        observeViewModel()
    }

    private fun initBinding() {
        binding.btnLogin.setOnClickListener {
            viewModel.login(
                binding.loginEtId.text.toString(),
                binding.loginEtPwd.text.toString()
            )
        }
        binding.loginTvSignup.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }
    }

    private fun observeViewModel() {
        viewModel.loginSuccess.observe(this) {
            if (it) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }
        viewModel.state.observe(this) { showToast(it) }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}