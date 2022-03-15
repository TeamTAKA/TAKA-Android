package com.taka.taka.data.datasource.remote

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    fun getClient(): Retrofit {
        val baseUrl = "https://asia-northeast3-taka-1dd38.cloudfunctions.net/api/"

        val interceptor = HttpLoggingInterceptor { Log.d("HttpLog", it) }
        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)).build()

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }
}