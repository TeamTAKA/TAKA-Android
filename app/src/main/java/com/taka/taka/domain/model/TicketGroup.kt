package com.taka.taka.domain.model

import com.google.gson.annotations.SerializedName

data class TicketGroup(
    @SerializedName("titleKor") val titleKr: String,
    @SerializedName("ticketList") val tickets: List<Ticket>
)
