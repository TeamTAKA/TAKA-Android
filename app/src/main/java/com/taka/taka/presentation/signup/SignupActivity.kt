package com.taka.taka.presentation.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.widget.doAfterTextChanged
import com.taka.taka.presentation.MainActivity
import com.taka.taka.R
import com.taka.taka.databinding.ActivitySignupBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignupActivity : AppCompatActivity() {

    private val viewModel: SignupViewModel by viewModels()
    private val binding: ActivitySignupBinding by lazy {
        ActivitySignupBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initBinding()
        observeViewModel()
    }

    private fun initBinding() {
        binding.signupTvLogin.setOnClickListener { finish() }
        binding.signupTvDuplicate.setOnClickListener {
            viewModel.checkId(binding.signupEtId.text.toString().trim())
        }
        binding.signupTvSignup.setOnClickListener {
            viewModel.signup(
                binding.signupEtId.text.toString().trim(),
                binding.signupEtPwd.text.toString().trim(),
                binding.signupEtPwdCheck.text.toString().trim()
            )
        }
        binding.signupEtId.doAfterTextChanged { viewModel.setIdUnchecked() }
    }

    private fun observeViewModel() {
        viewModel.isChecked.observe(this) { isChecked ->
            if (isChecked) {
                binding.signupTvDuplicate.apply {
                    text = getString(R.string.signup_duplicate_checked)
                    setTextColor(resources.getColor(R.color.blue))
                }
            } else {
                binding.signupTvDuplicate.apply {
                    text = getString(R.string.signup_duplicate_check)
                    setTextColor(resources.getColor(R.color.orange_red))
                }
            }
        }

        viewModel.signupSuccess.observe(this) {
            if (it) {
                val intent = Intent(this, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                finish()
            }
        }

        viewModel.state.observe(this) { showToast(it) }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}