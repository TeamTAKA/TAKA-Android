package com.taka.taka.data.datasource.remote.service

import com.taka.taka.data.datasource.remote.response.DefaultResponse
import com.taka.taka.data.datasource.remote.response.AuthResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UserService {
    @POST("auth/login")
    suspend fun login(@Body body: HashMap<String, Any>): AuthResponse

    @POST("auth/signup")
    suspend fun signup(@Body body: HashMap<String, Any>): AuthResponse

    @GET("auth/checkId/{id}")
    suspend fun checkId(@Path("id") id: String): DefaultResponse
}