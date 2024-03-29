package com.taka.taka.data.datasource.remote.service

import com.taka.taka.data.datasource.remote.response.DefaultResponse
import com.taka.taka.data.datasource.remote.response.GetTicketDetailResponse
import com.taka.taka.data.datasource.remote.response.GetTicketGroupsResponse
import com.taka.taka.data.datasource.remote.response.GetTicketsResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*

interface TicketService {
    @GET("ticket")
    suspend fun getTickets(): GetTicketsResponse

    @GET("ticket/group")
    suspend fun getTicketGroups(): GetTicketGroupsResponse

    @GET("ticket/detail/{ticket_idx}")
    suspend fun getTicketDetail(@Path("ticket_idx") ticketId: Int): GetTicketDetailResponse

    @Multipart
    @POST("ticket")
    suspend fun addTicket(
        @PartMap body: HashMap<String, RequestBody>,
        @Part file: MultipartBody.Part
    ): DefaultResponse

    @Multipart
    @PUT("ticket/detail/{ticketIdx}")
    suspend fun editTicket(
        @Path("ticketIdx") ticketId: Int,
        @PartMap body: HashMap<String, RequestBody>,
        @Part file: MultipartBody.Part
    ): DefaultResponse

    @DELETE("ticket/detail/{ticket_idx}")
    suspend fun deleteTicket(@Path("ticket_idx") ticketId: Int): DefaultResponse

    @POST("ticket/search")
    suspend fun searchTicket(@Body body: HashMap<String, Any>): GetTicketGroupsResponse
}