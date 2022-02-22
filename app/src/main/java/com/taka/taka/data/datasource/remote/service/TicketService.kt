package com.taka.taka.data.datasource.remote.service

import com.taka.taka.data.datasource.remote.response.DefaultResponse
import com.taka.taka.data.datasource.remote.response.GetTicketDetailResponse
import com.taka.taka.data.datasource.remote.response.GetTicketsResponse
import retrofit2.http.*

interface TicketService {
    @GET("/ticket/main/{user_idx}")
    suspend fun getTickets(@Path("user_idx") userId: Int): GetTicketsResponse

    @GET("/ticket/{ticket_idx}")
    suspend fun getTicketDetail(@Path("ticket_idx") ticketId: Int): GetTicketDetailResponse

    @POST("/ticket")
    suspend fun addTicket(body: HashMap<String, Any>): DefaultResponse

    @PUT("/ticket/{ticket_idx}")
    suspend fun editTicket(
        @Path("ticket_idx") ticketId: Int,
        body: HashMap<String, Any>
    ): DefaultResponse

    @DELETE("/ticket/{ticket_idx}")
    suspend fun deleteTicket(@Path("ticket_idx") ticketId: Int): DefaultResponse
}