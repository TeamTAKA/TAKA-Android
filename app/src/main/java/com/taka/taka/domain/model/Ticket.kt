package com.taka.taka.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Ticket(
    @SerializedName("ticketIdx") val id: Int,
    @SerializedName("coverImage") val img: String,
    @SerializedName("titleKor") val titleKr: String,
    @SerializedName("titleEng") val titleEn: String,
    @SerializedName("date") val date: String,
    @SerializedName("hall") val theater: String,
    @SerializedName("seat") val seat: String,
    @SerializedName("cast") val cast: String,
    @SerializedName("review") val review: String,
    @SerializedName("count") val count: Int,
) : Parcelable
