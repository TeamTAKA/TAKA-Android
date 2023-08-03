package com.taka.taka.domain.repository

import com.taka.taka.data.datasource.remote.response.DefaultResponse
import com.taka.taka.data.datasource.remote.response.GetTicketDetailResponse
import com.taka.taka.data.datasource.remote.response.GetTicketGroupsResponse
import com.taka.taka.data.datasource.remote.response.GetTicketsResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface TicketRepository {
    suspend fun getTickets(): GetTicketsResponse
    suspend fun getTicketGroups(): GetTicketGroupsResponse
    suspend fun getTicketDetail(ticketId: Int): GetTicketDetailResponse
    suspend fun addTicket(body: HashMap<String, RequestBody>, file: MultipartBody.Part): DefaultResponse
    suspend fun editTicket(ticketId: Int, body: HashMap<String, Any>): DefaultResponse
    suspend fun deleteTicket(ticketId: Int): DefaultResponse
}