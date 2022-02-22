package com.taka.taka.domain.repository

import com.taka.taka.data.datasource.remote.response.DefaultResponse

interface UserRepository {
    suspend fun signup(body: HashMap<String, Any>): DefaultResponse
    suspend fun login(body: HashMap<String, Any>): DefaultResponse
    suspend fun checkId(id: String): DefaultResponse
}