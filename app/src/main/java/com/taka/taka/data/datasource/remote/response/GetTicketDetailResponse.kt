package com.taka.taka.data.datasource.remote.response

import com.taka.taka.domain.model.Ticket

data class GetTicketDetailResponse(
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: Ticket
)