package com.taka.taka.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.taka.taka.R
import com.taka.taka.databinding.ActivityAddBinding
import com.taka.taka.viewmodel.AddViewModel

class AddActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddBinding
    private lateinit var viewModel: AddViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(AddViewModel::class.java)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_add)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.addIvBack.setOnClickListener { finish() }
    }
}