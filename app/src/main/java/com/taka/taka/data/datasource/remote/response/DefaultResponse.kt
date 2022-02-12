package com.taka.taka.data.datasource.remote.response

data class DefaultResponse(
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: Int
)