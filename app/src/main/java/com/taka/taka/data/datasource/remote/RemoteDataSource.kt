package com.taka.taka.data.datasource.remote

import com.taka.taka.data.datasource.remote.service.TicketService
import com.taka.taka.data.datasource.remote.service.UserService
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val userService: UserService,
    private val ticketService: TicketService
) {
    suspend fun signup(body: HashMap<String, Any>) = userService.signup(body)

    suspend fun login(body: HashMap<String, Any>) = userService.login(body)

    suspend fun checkId(id: String) = userService.checkId(id)

    suspend fun getTickets() = ticketService.getTickets()

    suspend fun getTicketGroups() = ticketService.getTicketGroups()

    suspend fun getTicketDetail(ticketId: Int) = ticketService.getTicketDetail(ticketId)

    suspend fun addTicket(body: HashMap<String, RequestBody>, file: MultipartBody.Part) = ticketService.addTicket(body, file)

    suspend fun editTicket(
        ticketId: Int,
        body: HashMap<String, RequestBody>,
        file: MultipartBody.Part,
    ) = ticketService.editTicket(ticketId, body, file)

    suspend fun deleteTicket(ticketId: Int) = ticketService.deleteTicket(ticketId)
}