package com.taka.taka.domain.repository

import com.taka.taka.data.datasource.remote.response.DefaultResponse
import com.taka.taka.data.datasource.remote.response.AuthResponse

interface UserRepository {
    suspend fun signup(body: HashMap<String, Any>): AuthResponse
    suspend fun login(body: HashMap<String, Any>): AuthResponse
    suspend fun checkId(id: String): DefaultResponse
    suspend fun setAccessToken(accessToken: String)
    fun getAccessToken(): String?
    suspend fun removeAccessToken()
    suspend fun setUserIdx(userIdx: Int)
    suspend fun getUserIdx(): Int?
    fun removeUserIdx()
    suspend fun setUserId(userId: String)
    fun getUserId(): String
    fun removeUserId()
}