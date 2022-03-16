package com.taka.taka.domain.repository

import com.taka.taka.data.datasource.remote.response.DefaultResponse
import com.taka.taka.data.datasource.remote.response.SignupResponse

interface UserRepository {
    suspend fun signup(body: HashMap<String, Any>): SignupResponse
    suspend fun login(body: HashMap<String, Any>): DefaultResponse
    suspend fun checkId(id: String): DefaultResponse
    suspend fun setAccessToken(accessToken: String)
    suspend fun getAccessToken(): String?
    suspend fun setUserIdx(userIdx: Int)
    suspend fun getUserIdx(): Int?
}