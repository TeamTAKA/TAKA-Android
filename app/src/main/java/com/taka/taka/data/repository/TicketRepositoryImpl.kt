package com.taka.taka.data.repository

import com.taka.taka.data.datasource.remote.RemoteDataSource
import com.taka.taka.data.datasource.remote.response.DefaultResponse
import com.taka.taka.data.datasource.remote.response.GetTicketDetailResponse
import com.taka.taka.data.datasource.remote.response.GetTicketsResponse
import com.taka.taka.domain.repository.TicketRepository
import javax.inject.Inject

class TicketRepositoryImpl @Inject constructor(private val remoteDataSource: RemoteDataSource) :
    TicketRepository {
    override suspend fun getTickets(userId: Int): GetTicketsResponse =
        remoteDataSource.getTickets(userId)

    override suspend fun getTicketDetail(ticketId: Int): GetTicketDetailResponse =
        remoteDataSource.getTicketDetail(ticketId)

    override suspend fun addTicket(body: HashMap<String, Any>): DefaultResponse =
        remoteDataSource.addTicket(body)

    override suspend fun editTicket(ticketId: Int, body: HashMap<String, Any>): DefaultResponse =
        remoteDataSource.editTicket(ticketId, body)

    override suspend fun deleteTicket(ticketId: Int): DefaultResponse =
        remoteDataSource.deleteTicket(ticketId)

}