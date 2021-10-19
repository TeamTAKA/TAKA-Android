package com.taka.taka

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.taka.taka.databinding.ActivityMainBinding
import com.taka.taka.ui.BottomSheet

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        navController = findNavController(R.id.nav_host_fragment)

        binding.mainIvAdd.setOnClickListener {
            // 다이얼로그 띄우기
            BottomSheet().show(supportFragmentManager, getString(R.string.add_ticket))
        }
        binding.mainIvHome.setOnClickListener {
            if (navController.currentDestination?.id == R.id.search_fragment)
                navController.navigate(R.id.action_searchFragment_to_homeFragment)
        }
        binding.mainIvSearch.setOnClickListener {
            if (navController.currentDestination?.id == R.id.home_fragment)
                navController.navigate(R.id.action_homeFragment_to_searchFragment)
        }
    }
}