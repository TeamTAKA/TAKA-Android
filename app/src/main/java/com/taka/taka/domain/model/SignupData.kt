package com.taka.taka.domain.model

import com.google.gson.annotations.SerializedName

data class SignupData(
    @SerializedName("userIdx") val userIdx: Int,
    @SerializedName("accesstoken") val accessToken: String
)