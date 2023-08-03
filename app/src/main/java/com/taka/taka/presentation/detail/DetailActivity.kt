package com.taka.taka.presentation.detail

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.taka.taka.R
import com.taka.taka.databinding.ActivityDetailBinding
import com.taka.taka.domain.model.Ticket
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {
    private val binding: ActivityDetailBinding by lazy {
        ActivityDetailBinding.inflate(
            layoutInflater
        )
    }
    private val viewModel: DetailViewModel by viewModels()
    private var ticketId: Int? = null
    private var ticket: Ticket? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.detailIbBack.setOnClickListener { finish() }
        binding.detailIbMenu.setOnClickListener {
            registerForContextMenu(it)
            openContextMenu(it)
            unregisterForContextMenu(it)
        }
        binding.detailTvReview.movementMethod = ScrollingMovementMethod()

        ticketId = intent.extras?.getInt(TICKET_ID_KEY)
        lifecycleScope.launch {
            viewModel.getTicket(ticketId!!)?.let {
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

                binding.tvNumber.text = it.count.toString()
                Glide
                    .with(binding.detailIvPoster)
                    .load(it.img)
                    .into(binding.detailIvPoster)
            }
        }
    }

    companion object {
        const val TICKET_ID_KEY = "ticketId"
    }
}