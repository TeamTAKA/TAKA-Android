package com.taka.taka.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.taka.taka.domain.model.Ticket
import com.taka.taka.domain.repository.TicketRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val ticketRepository: TicketRepository
) : ViewModel() {
    private val _state: MutableLiveData<String> = MutableLiveData()
    val state: LiveData<String>
        get() = _state
    private val _deleteSuccess: MutableLiveData<Boolean> = MutableLiveData()
    val deleteSuccess: LiveData<Boolean>
        get() = _deleteSuccess

    suspend fun getTicket(ticketId: Int): Ticket? {
        var ticket: Ticket? = null
        try {
            val response = ticketRepository.getTicketDetail(ticketId)

            if (response.success) {
                ticket = response.data
            }
        } catch (e: Exception) {
            _state.value = "티켓 정보를 불러오는데 실패했습니다"
            e.printStackTrace()
        }
        return ticket
    }

    suspend fun deleteTicket(ticketId: Int) {
        try {
            val response = ticketRepository.deleteTicket(ticketId)
            _deleteSuccess.value = response.success
        } catch (e: Exception) {
            _state.value = "티켓을 삭제하지 못했습니다"
            e.printStackTrace()
        }
    }
}