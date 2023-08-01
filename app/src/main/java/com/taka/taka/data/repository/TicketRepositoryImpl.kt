package com.taka.taka.data.repository

import com.taka.taka.data.datasource.remote.RemoteDataSource
import com.taka.taka.data.datasource.remote.response.DefaultResponse
import com.taka.taka.data.datasource.remote.response.GetTicketDetailResponse
import com.taka.taka.data.datasource.remote.response.GetTicketsResponse
import com.taka.taka.domain.repository.TicketRepository
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

class TicketRepositoryImpl @Inject constructor(private val remoteDataSource: RemoteDataSource) :
    TicketRepository {
    override suspend fun getTickets(): GetTicketsResponse =
        remoteDataSource.getTickets()

    override suspend fun getTicketDetail(ticketId: Int): GetTicketDetailResponse =
        remoteDataSource.getTicketDetail(ticketId)

    override suspend fun addTicket(body: HashMap<String, RequestBody>, file: MultipartBody.Part): DefaultResponse =
        remoteDataSource.addTicket(body, file)

    override suspend fun editTicket(ticketId: Int, body: HashMap<String, Any>): DefaultResponse =
        remoteDataSource.editTicket(ticketId, body)

    override suspend fun deleteTicket(ticketId: Int): DefaultResponse =
        remoteDataSource.deleteTicket(ticketId)

}