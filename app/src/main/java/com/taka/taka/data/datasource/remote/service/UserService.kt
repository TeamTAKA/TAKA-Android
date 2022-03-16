package com.taka.taka.data.datasource.remote.service

import com.taka.taka.data.datasource.remote.response.DefaultResponse
import com.taka.taka.data.datasource.remote.response.SignupResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UserService {
    @POST("user/signin")
    suspend fun login(body: HashMap<String, Any>): DefaultResponse

    @POST("auth/signup")
    suspend fun signup(@Body body: HashMap<String, Any>): SignupResponse

    @GET("auth/checkId/{id}")
    suspend fun checkId(@Path("id") id: String): DefaultResponse
}