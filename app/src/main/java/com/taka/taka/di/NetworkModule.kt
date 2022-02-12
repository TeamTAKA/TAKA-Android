package com.taka.taka.di

import com.taka.taka.data.datasource.remote.RetrofitClient
import com.taka.taka.data.datasource.remote.service.TicketService
import com.taka.taka.data.datasource.remote.service.UserService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    @Singleton
    fun provideUserService(): UserService {
        return RetrofitClient.getClient().create(UserService::class.java)
    }

    @Provides
    @Singleton
    fun provideTicketService(): TicketService {
        return RetrofitClient.getClient().create(TicketService::class.java)
    }
}