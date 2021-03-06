package com.taka.taka.presentation.mypage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.taka.taka.databinding.ActivityMypageBinding
import com.taka.taka.databinding.DialogContactBinding

class MypageActivity : AppCompatActivity() {

    private val binding: ActivityMypageBinding by lazy {
        ActivityMypageBinding.inflate(
            layoutInflater
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.mypageIvClose.setOnClickListener { finish() }
        binding.mypageTvContact.setOnClickListener { showBottomSheet() }
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