package com.taka.taka.domain.model

import com.google.gson.annotations.SerializedName

data class AuthData(
    @SerializedName("userIdx") val userIdx: Int,
    @SerializedName("accessToken") val accessToken: String
)