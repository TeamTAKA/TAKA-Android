package com.taka.taka.di

import com.taka.taka.data.repository.TicketRepositoryImpl
import com.taka.taka.data.repository.UserRepositoryImpl
import com.taka.taka.domain.repository.TicketRepository
import com.taka.taka.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun bindUserRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository

    @Binds
    fun bindTicketRepository(ticketRepositoryImpl: TicketRepositoryImpl): TicketRepository
}