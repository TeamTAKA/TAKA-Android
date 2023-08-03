package com.taka.taka.data.datasource.remote.response

import com.taka.taka.domain.model.TicketGroup

data class GetTicketGroupsResponse(
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: List<TicketGroup>
)