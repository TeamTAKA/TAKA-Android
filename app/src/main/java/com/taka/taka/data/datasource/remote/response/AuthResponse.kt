package com.taka.taka.data.datasource.remote.response

import com.taka.taka.domain.model.AuthData

data class AuthResponse(
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: AuthData
)