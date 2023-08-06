package com.taka.taka.presentation.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.taka.taka.R
import com.taka.taka.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {

    companion object {
        fun newInstance() = SearchFragment()
    }

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SearchViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val keywordAdapter = RecentKeywordAdapter { keyword ->
            //검색
        }
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
            viewModel.addKeyword(keyword)
        }

        viewModel.getRecentKeywords().let {
            keywordAdapter.setKeywordList(it)
            binding.tvRecent.isVisible = it.isNotEmpty()
            binding.tvDeleteAll.isVisible = it.isNotEmpty()
            binding.rvKeywords.isVisible = it.isNotEmpty()
        }
    }

}