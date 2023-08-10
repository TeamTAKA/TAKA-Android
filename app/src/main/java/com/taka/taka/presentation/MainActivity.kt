package com.taka.taka.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.taka.taka.R
import com.taka.taka.databinding.ActivityMainBinding
import com.taka.taka.presentation.add.AddActivity
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
            startActivity(Intent(this, AddActivity::class.java))
        }
        binding.mainIvHome.setOnClickListener {
            if (navController.currentDestination?.id == R.id.search_fragment) {
                navController.navigateUp()
            }
        }
        binding.mainIvSearch.setOnClickListener {
            if (navController.currentDestination?.id == R.id.home_fragment)
                navController.navigate(R.id.action_homeFragment_to_searchFragment)
        }
    }
}