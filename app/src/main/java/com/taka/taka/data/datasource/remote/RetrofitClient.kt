package com.taka.taka.data.datasource.remote

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    fun getClient(): Retrofit {
        val baseUrl = "https://asia-northeast3-taka-1dd38.cloudfunctions.net/api/"

        val client = OkHttpClient.Builder().build()

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }
}