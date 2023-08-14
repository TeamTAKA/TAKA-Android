package com.taka.taka.presentation.detail

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View.MeasureSpec.UNSPECIFIED
import android.view.ViewGroup
import android.widget.PopupWindow
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.taka.taka.R
import com.taka.taka.databinding.ActivityDetailBinding
import com.taka.taka.databinding.PopupDetailBinding
import com.taka.taka.domain.model.Ticket
import com.taka.taka.presentation.add.AddActivity
import com.taka.taka.presentation.utils.dpToPx
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {
    private val binding: ActivityDetailBinding by lazy {
        ActivityDetailBinding.inflate(
            layoutInflater
        )
    }
    private val popupBinding by lazy { PopupDetailBinding.inflate(layoutInflater) }
    private val popupWindow by lazy {
        PopupWindow(
            popupBinding.root,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            true
        )
    }
    private val viewModel: DetailViewModel by viewModels()
    private var ticketId: Int? = null
    private var ticket: Ticket? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        window.statusBarColor = getColor(R.color.white)
        ticketId = intent.extras?.getInt(TICKET_ID_KEY)
        setPopupWindow()

        binding.detailIbBack.setOnClickListener { finish() }
        binding.detailIbMenu.setOnClickListener {
            popupBinding.root.measure(UNSPECIFIED, UNSPECIFIED)
            popupWindow.showAsDropDown(
                binding.detailIbMenu,
                -popupBinding.root.measuredWidth + 20.dpToPx.toInt(),
                0
            )
        }
        binding.detailTvReview.movementMethod = ScrollingMovementMethod()
        binding.btnAddReview.setOnClickListener {
            startActivity(Intent(this, AddActivity::class.java).apply {
                putExtra(TICKET_KEY, ticket)
            })
        }

        viewModel.deleteSuccess.observe(this) { success ->
            if (success) {
                setResult(Activity.RESULT_OK)
                popupWindow.dismiss()
                finish()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        ticketId?.let { getTicketDetail(it) }
    }

    private fun getTicketDetail(ticketId: Int) {
        lifecycleScope.launch {
            viewModel.getTicket(ticketId)?.let {
                ticket = it
                binding.detailTvDate.text = it.date
                binding.detailTvTitleEng.text = it.titleEn.ifBlank { getString(R.string.ticket_title_default) }
                binding.detailTvTitleKor.text = it.titleKr
                binding.detailTvSeatLabel.isVisible = !(it.theater.isBlank() || it.seat.isBlank())
                binding.detailTvSeat.isVisible = !(it.theater.isBlank() || it.seat.isBlank())
                binding.detailTvSeat.text = "${it.theater} / ${it.seat}"
                binding.detailTvCastLabel.isVisible = it.cast.isNotBlank()
                binding.detailTvCast.isVisible = it.cast.isNotBlank()
                binding.detailTvCast.text = it.cast
                binding.detailTvReview.text = it.review
                binding.detailTvReview.isVisible = it.review.isNotBlank()
                binding.tvReviewAddLabel.isVisible = it.review.isBlank()
                binding.btnAddReview.isVisible = it.review.isBlank()

                binding.tvNumber.text = it.count.toString()
                Glide
                    .with(binding.detailIvPoster)
                    .load(it.img)
                    .into(binding.detailIvPoster)
            }
        }
    }

    private fun setPopupWindow() {
        popupWindow.elevation = 10.dpToPx
        popupBinding.menuEdit.setOnClickListener {
            startActivity(Intent(this@DetailActivity, AddActivity::class.java).apply {
                putExtra(TICKET_KEY, ticket)
            })
            popupWindow.dismiss()
        }
        val deleteDialog = DeleteDialog(viewModel, ticketId)
        popupBinding.menuDelete.setOnClickListener {
            deleteDialog.show(supportFragmentManager, deleteDialog.tag)
            popupWindow.dismiss()
        }
    }

    companion object {
        const val TICKET_ID_KEY = "ticketId"
        const val TICKET_KEY = "ticket"
    }
}