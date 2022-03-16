package com.taka.taka.data.datasource.remote.response

import com.taka.taka.domain.model.SignupData

data class SignupResponse(
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: SignupData
)