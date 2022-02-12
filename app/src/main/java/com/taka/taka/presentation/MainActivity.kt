package com.taka.taka.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.taka.taka.R
import com.taka.taka.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

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