package com.taka.taka.presentation.add

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.core.widget.addTextChangedListener
import com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_SHORT
import com.google.android.material.snackbar.Snackbar
import com.taka.taka.R
import com.taka.taka.databinding.ActivityAddBinding
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

@AndroidEntryPoint
class AddActivity : AppCompatActivity() {

    private val binding: ActivityAddBinding by lazy { ActivityAddBinding.inflate(layoutInflater) }
    private val viewModel: AddViewModel by viewModels()
    var filepath: String? = null
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
                filepath = fullPhotoUri?.path
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
        binding.addEtTitleKr.addTextChangedListener {
            checkAddable()
        }
        binding.addEtDate.addTextChangedListener {
            checkAddable()
        }

        binding.addTvAdd.setOnClickListener {
            val titleKor = binding.addEtTitleKr.text.toString()
            val titleEng = binding.addEtTitleEn.text.toString()
            val date = binding.addEtDate.text.toString()
            val hall = binding.addEtTheater.text.toString()
            val seat = binding.addEtSeat.text.toString()
            val cast = binding.addEtCast.text.toString()
            val seller = binding.addEtReview.text.toString()
            val review = binding.addEtReview.text.toString()

            if (filepath == null) {
                Toast.makeText(this, getString(R.string.ticket_image_empty), Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val file = bitmapToFile(binding.addIvPoster.drawable.toBitmap()) ?: return@setOnClickListener

            viewModel.addTicket(
                file,
                titleKor,
                titleEng,
                date,
                hall,
                seat,
                cast,
                seller,
                review
            )
        }

        viewModel.addSuccess.observe(this) { isSuccess ->
            if (isSuccess) {
                finish()
            } else {
                Toast.makeText(this, getString(R.string.error_ticket_add_failed), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun bitmapToFile(bitmap: Bitmap): File? {
        val directory = cacheDir
        val file = File(directory, "tmp.png")

        try {
            val outputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
            outputStream.flush()
            outputStream.close()
            return file
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return null
    }

    private fun checkAddable() {
        binding.addTvAdd.isEnabled =
            binding.addEtTitleKr.text.isNotBlank() && binding.addEtDate.text.isNotBlank()
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