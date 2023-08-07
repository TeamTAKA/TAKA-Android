package com.taka.taka.presentation.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.taka.taka.domain.model.TicketGroup
import com.taka.taka.domain.repository.KeywordRepository
import com.taka.taka.domain.repository.TicketRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchResultViewModel @Inject constructor(
    private val ticketRepository: TicketRepository,
    private val keywordRepository: KeywordRepository,
) : ViewModel()  {
    private val _state: MutableLiveData<String> = MutableLiveData()
    val state: LiveData<String>
        get() = _state
    private val _searchResults: MutableLiveData<List<TicketGroup>> = MutableLiveData()
    val searchResults: LiveData<List<TicketGroup>>
        get() = _searchResults

    fun search(keyword: String): List<TicketGroup> {
        var tickets = emptyList<TicketGroup>()
        viewModelScope.launch {
            try {
                keywordRepository.addRecentKeyword(keyword)

                val body = hashMapOf<String, Any>()
                body["keyword"] = keyword

                val response = ticketRepository.searchTickets(body)
                if (response.success) {
                    _searchResults.value = response.data
                } else {
                    _state.value = response.message
                }
            } catch (e: Exception) {
                _state.value = "검색에 실패했습니다"
                e.printStackTrace()
            }
        }
        return tickets
    }
}