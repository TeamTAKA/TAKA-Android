package com.taka.taka.presentation.add

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_SHORT
import com.google.android.material.snackbar.Snackbar
import com.taka.taka.databinding.ActivityAddBinding

class AddActivity : AppCompatActivity() {

    private val binding: ActivityAddBinding by lazy { ActivityAddBinding.inflate(layoutInflater) }
    private val viewModel: AddViewModel by lazy {
        ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(AddViewModel::class.java)
    }
    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                // 갤러리로 이동
                openGallery()
            } else {
                Snackbar.make(binding.root, "권한이 필요합니다.", LENGTH_SHORT)
            }
        }
    private val getPhotoLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {
                val fullPhotoUri: Uri? = it.data?.data

                // 갤러리에서 가져온 사진 이미지뷰에 로드
                binding.addIvPoster.setImageURI(fullPhotoUri)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.addIvBack.setOnClickListener { finish() }

        // 포스터 이미지 선택
        binding.addIvEdit.setOnClickListener {
            // 권한 확인
            when (PackageManager.PERMISSION_GRANTED) {
                ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ) -> {
                    // 권한 허용된 경우
                    openGallery()
                }
                else -> {
                    requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
                }
            }
        }
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_GET_CONTENT).apply {
            type = "image/*"
        }
        // TODO 패키지 가시성
        // if (intent.resolveActivity(packageManager) != null)
        getPhotoLauncher.launch(intent)
    }
}