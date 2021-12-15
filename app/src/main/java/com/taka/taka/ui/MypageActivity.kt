package com.taka.taka.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.taka.taka.R
import com.taka.taka.databinding.ActivityMypageBinding

class MypageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMypageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_mypage)

        binding.mypageIvClose.setOnClickListener { finish() }
    }
}