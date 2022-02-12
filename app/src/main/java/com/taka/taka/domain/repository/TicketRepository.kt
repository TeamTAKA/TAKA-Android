package com.taka.taka.domain.repository

import com.taka.taka.data.datasource.remote.response.DefaultResponse
import com.taka.taka.data.datasource.remote.response.GetTicketDetailResponse
import com.taka.taka.data.datasource.remote.response.GetTicketsResponse

interface TicketRepository {
    suspend fun getTickets(userId: Int): GetTicketsResponse
    suspend fun getTicketDetail(ticketId: Int): GetTicketDetailResponse
    suspend fun addTicket(body: HashMap<String, Any>): DefaultResponse
    suspend fun editTicket(ticketId: Int, body: HashMap<String, Any>): DefaultResponse
    suspend fun deleteTicket(ticketId: Int): DefaultResponse
}