package com.taka.taka.presentation.search

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.taka.taka.R
import com.taka.taka.databinding.ActivitySearchResultBinding
import com.taka.taka.presentation.detail.DetailActivity
import com.taka.taka.presentation.home.adapter.TicketGroupAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchResultActivity : AppCompatActivity() {
    private val viewModel: SearchResultViewModel by viewModels()
    private val binding: ActivitySearchResultBinding by lazy {
        ActivitySearchResultBinding.inflate(
            layoutInflater
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.rvTickets.adapter = TicketGroupAdapter { ticketId ->
            startActivity(
                Intent(this, DetailActivity::class.java).putExtra(
                    DetailActivity.TICKET_ID_KEY,
                    ticketId
                )
            )
        }

        val keyword = intent.extras?.getString(KEYWORD_KEY).orEmpty()
        search(keyword)
        binding.etKeyword.setText(keyword)
        binding.ivSearch.setOnClickListener {
            val text = binding.etKeyword.text.toString()
            if (text.isBlank()) {
                Toast.makeText(this, getString(R.string.search_keyword_empty), Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            search(text)
        }

        viewModel.searchResults.observe(this) { tickets ->
            (binding.rvTickets.adapter as TicketGroupAdapter).setTicketGroupList(tickets)
            binding.tvEmpty.isVisible = tickets.isEmpty()
            setResult(Activity.RESULT_OK)
        }
    }

    private fun search(keyword: String) {
        lifecycleScope.launch {
            viewModel.search(keyword)
        }
    }

    companion object {
        const val KEYWORD_KEY = "keyword"
    }
}