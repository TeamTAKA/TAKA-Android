package com.taka.taka.data.datasource.remote

import com.taka.taka.data.datasource.remote.service.TicketService
import com.taka.taka.data.datasource.remote.service.UserService

class RemoteDataSource(
    private val userService: UserService,
    private val ticketService: TicketService
) {
    suspend fun signup(body: HashMap<String, Any>) = userService.signup(body)

    suspend fun login(body: HashMap<String, Any>) = userService.login(body)

    suspend fun getTickets(userId: Int) = ticketService.getTickets(userId)

    suspend fun getTicketDetail(ticketId: Int) = ticketService.getTicketDetail(ticketId)

    suspend fun addTicket(body: HashMap<String, Any>) = ticketService.addTicket(body)

    suspend fun editTicket(ticketId: Int, body: HashMap<String, Any>) =
        ticketService.editTicket(ticketId, body)

    suspend fun deleteTicket(ticketId: Int) = ticketService.deleteTicket(ticketId)
}