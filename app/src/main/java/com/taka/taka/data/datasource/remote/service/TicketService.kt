package com.taka.taka.data.datasource.remote.service

import com.taka.taka.data.datasource.remote.response.DefaultResponse
import com.taka.taka.data.datasource.remote.response.GetTicketDetailResponse
import com.taka.taka.data.datasource.remote.response.GetTicketsResponse
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT

interface TicketService {
    @GET("/ticket/main/{user_idx}")
    suspend fun getTickets(user_idx: Int): GetTicketsResponse

    @GET("/ticket/{ticket_idx}")
    suspend fun getTicketDetail(ticket_idx: Int): GetTicketDetailResponse

    @POST("/ticket")
    suspend fun addTicket(body: HashMap<String, Any>): DefaultResponse

    @PUT("/ticket/{ticket_idx}")
    suspend fun editTicket(ticket_idx: Int, body: HashMap<String, Any>): DefaultResponse

    @DELETE("/ticket/{ticket_idx}")
    suspend fun deleteTicket(ticket_idx: Int): DefaultResponse
}