package com.taka.taka.presentation.detail

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.lifecycleScope
import com.taka.taka.databinding.DialogDeleteBinding
import kotlinx.coroutines.launch

class DeleteDialog(private val viewModel: DetailViewModel, private val ticketId: Int?) :
    DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DialogDeleteBinding.inflate(layoutInflater, container, false)
        dialog?.apply {
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
        binding.ivClose.setOnClickListener { dismiss() }
        binding.tvDelete.setOnClickListener {
            lifecycleScope.launch {
                ticketId?.let { id ->
                    viewModel.deleteTicket(id)
                }
            }
        }
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        val display = resources.displayMetrics

        val window: Window = dialog?.window ?: return
        val params: WindowManager.LayoutParams = window.attributes
        params.width = (display.widthPixels * 0.8).toInt()

        dialog?.window!!.attributes = params
    }
}