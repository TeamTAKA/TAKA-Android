package com.taka.taka.presentation.mypage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.taka.taka.R
import com.taka.taka.databinding.ActivityMypageBinding
import com.taka.taka.databinding.DialogContactBinding
import com.taka.taka.presentation.login.LoginActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MypageActivity : AppCompatActivity() {

    private val viewModel: MypageViewModel by viewModels()
    private val binding: ActivityMypageBinding by lazy {
        ActivityMypageBinding.inflate(
            layoutInflater
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        window.statusBarColor = getColor(R.color.white)

        val nickname = "${viewModel.userId}님!"
        binding.mypageTvNickname.text = nickname
        binding.mypageIvClose.setOnClickListener { finish() }
        binding.mypageTvContact.setOnClickListener { showBottomSheet() }
        binding.mypageTvLogout.setOnClickListener {
            lifecycleScope.launch { viewModel.logout() }
            startActivity(
                Intent(
                    this,
                    LoginActivity::class.java
                ).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            )
        }
    }

    private fun showBottomSheet() {
        val bottomSheetBinding = DialogContactBinding.inflate(layoutInflater)
        val bottomSheetDialog = BottomSheetDialog(this)
        bottomSheetDialog.setContentView(bottomSheetBinding.root)

        val displayMetrics = DisplayMetrics()
        this.windowManager?.defaultDisplay?.getMetrics(displayMetrics)
        val deviceHeight = displayMetrics.heightPixels
        bottomSheetBinding.root.layoutParams.height = deviceHeight - 30

        bottomSheetBinding.contactIbClose.setOnClickListener { bottomSheetDialog.dismiss() }
        bottomSheetBinding.contactTvWrite.setOnClickListener {
            bottomSheetDialog.dismiss()
            //TODO: 서버로 보내기
        }

        bottomSheetDialog.show()
    }
}