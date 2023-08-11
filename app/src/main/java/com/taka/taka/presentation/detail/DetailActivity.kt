package com.taka.taka.presentation.detail

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.taka.taka.R
import com.taka.taka.databinding.ActivityDetailBinding
import com.taka.taka.domain.model.Ticket
import com.taka.taka.presentation.add.AddActivity
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
        binding.tvAddReview.setOnClickListener {
            startActivity(Intent(this, AddActivity::class.java).apply {
                putExtra(TICKET_KEY, ticket)
            })
        }

        ticketId = intent.extras?.getInt(TICKET_ID_KEY)

        viewModel.deleteSuccess.observe(this) { success ->
            if (success) {
                setResult(Activity.RESULT_OK)
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
                binding.tvAddReview.isVisible = it.review.isBlank()

                binding.tvNumber.text = it.count.toString()
                Glide
                    .with(binding.detailIvPoster)
                    .load(it.img)
                    .into(binding.detailIvPoster)
            }
        }
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.ticket_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_delete -> {
                lifecycleScope.launch {
                    ticketId?.let {
                        viewModel.deleteTicket(it)
                    }
                }
            }
            R.id.menu_edit -> {
                startActivity(Intent(this, AddActivity::class.java).apply {
                    putExtra(TICKET_KEY, ticket)
                })
            }
            R.id.menu_share -> {

            }
        }
        return super.onContextItemSelected(item)
    }

    companion object {
        const val TICKET_ID_KEY = "ticketId"
        const val TICKET_KEY = "ticket"
    }
}