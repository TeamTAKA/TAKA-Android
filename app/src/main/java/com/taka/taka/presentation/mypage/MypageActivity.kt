package com.taka.taka.presentation.mypage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.taka.taka.R
import com.taka.taka.databinding.ActivityMypageBinding
import com.taka.taka.databinding.DialogContactBinding

class MypageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMypageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_mypage)

        binding.mypageIvClose.setOnClickListener { finish() }
        binding.mypageTvContact.setOnClickListener { showBottomSheet() }
    }

    private fun showBottomSheet() {
        val bottomSheetView = layoutInflater.inflate(R.layout.dialog_contact, null)
        val bottomSheetBinding = DataBindingUtil.inflate<DialogContactBinding>(
            layoutInflater,
            R.layout.dialog_contact,
            bottomSheetView as ViewGroup,
            false
        )
        bottomSheetBinding.lifecycleOwner = this
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