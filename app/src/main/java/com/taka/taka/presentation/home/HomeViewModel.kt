package com.taka.taka.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.taka.taka.domain.model.Ticket
import com.taka.taka.domain.model.TicketGroup
import com.taka.taka.domain.repository.TicketRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val ticketRepository: TicketRepository
) : ViewModel() {
    private val _state: MutableLiveData<String> = MutableLiveData()
    val state: LiveData<String>
        get() = _state
    suspend fun getTickets(): List<Ticket> {
        var tickets = emptyList<Ticket>()
        try {
            val response = ticketRepository.getTickets()

            if (response.success) {
                tickets = response.data
            }
        } catch (e: Exception) {
            _state.value = "티켓 리스트 조회에 실패했습니다"
            e.printStackTrace()
        }
        return tickets
    }

    suspend fun getTicketGroups(): List<TicketGroup> {
        var ticketGroups = emptyList<TicketGroup>()
        try {
            val response = ticketRepository.getTicketGroups()

            if (response.success) {
                ticketGroups = response.data
            }
        } catch (e: Exception) {
            _state.value = "티켓 그룹 조회에 실패했습니다"
            e.printStackTrace()
        }
        return ticketGroups
    }
}