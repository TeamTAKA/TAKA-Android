package com.taka.taka.presentation.search

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.taka.taka.R
import com.taka.taka.databinding.FragmentSearchBinding
import com.taka.taka.presentation.search.SearchResultActivity.Companion.KEYWORD_KEY

class SearchFragment : Fragment() {

    companion object {
        fun newInstance() = SearchFragment()
    }

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SearchViewModel by activityViewModels()
    private val resultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            binding.etKeyword.text.clear()
            getRecentKeywords()
        }
    }
    private val keywordAdapter: RecentKeywordAdapter by lazy {
        RecentKeywordAdapter { keyword ->
            //검색
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.rvKeywords.adapter = keywordAdapter
        binding.ivSearch.setOnClickListener {
            val keyword = binding.etKeyword.text.toString().trim()

            if (keyword.isEmpty()) {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.search_keyword_empty),
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }
            resultLauncher.launch(Intent(context, SearchResultActivity::class.java).apply {
                putExtra(KEYWORD_KEY, keyword)
            })
        }

        getRecentKeywords()
    }

    private fun getRecentKeywords() {
        viewModel.getRecentKeywords().let {
            keywordAdapter.setKeywordList(it)
            binding.tvRecent.isVisible = it.isNotEmpty()
            binding.tvDeleteAll.isVisible = it.isNotEmpty()
            binding.rvKeywords.isVisible = it.isNotEmpty()
        }
    }
}