package com.taka.taka.data.datasource.remote

import android.util.Log
import com.taka.taka.data.datasource.local.LocalDataSource
import com.taka.taka.data.datasource.remote.service.TicketService
import com.taka.taka.data.datasource.remote.service.UserService
import com.taka.taka.data.repository.UserRepositoryImpl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    val repository: UserRepositoryImpl = UserRepositoryImpl(
        RemoteDataSource(
            getClient().create(UserService::class.java),
            getClient().create(TicketService::class.java)
        ), LocalDataSource()
    )

    fun getClient(): Retrofit {
        val baseUrl = "https://taka-server.com/"

        val baseInterceptor = Interceptor { chain ->
            val builder = chain.request().newBuilder()
                .addHeader("accessToken", repository.getAccessToken().toString())
                .build()
            chain.proceed(builder)
        }
        val interceptor = HttpLoggingInterceptor { Log.d("HttpLog", it) }
        val client = OkHttpClient.Builder()
            .addInterceptor(baseInterceptor)
            .addInterceptor(interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)).build()

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }
}