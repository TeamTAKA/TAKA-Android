package com.taka.taka.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.taka.taka.domain.repository.KeywordRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val keywordRepository: KeywordRepository
) : ViewModel() {
    fun addKeyword(keyword: String) {
        viewModelScope.launch {
            keywordRepository.addRecentKeyword(keyword)
        }
    }

}