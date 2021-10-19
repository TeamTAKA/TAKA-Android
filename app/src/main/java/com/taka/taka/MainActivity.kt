package com.taka.taka

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.taka.taka.databinding.ActivityMainBinding
import com.taka.taka.ui.BottomSheet

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this

        binding.mainBtnAdd.setOnClickListener {
            // 다이얼로그 띄우기
            BottomSheet().show(supportFragmentManager, getString(R.string.add_ticket))
        }
    }
}