package com.taka.taka.data.datasource.remote.service

import com.taka.taka.data.datasource.remote.response.DefaultResponse
import retrofit2.http.GET
import retrofit2.http.POST

interface UserService {
    @POST("/user/signin")
    suspend fun login(body: HashMap<String, Any>): DefaultResponse

    @POST("/user/signup")
    suspend fun signup(body: HashMap<String, Any>): DefaultResponse

    @GET("/auth/checkId/{id}")
    suspend fun checkId(id: String): DefaultResponse
}