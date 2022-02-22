package com.taka.taka.data.repository

import com.taka.taka.data.datasource.remote.RemoteDataSource
import com.taka.taka.data.datasource.remote.response.DefaultResponse
import com.taka.taka.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val remoteDataSource: RemoteDataSource) :
    UserRepository {
    override suspend fun signup(body: HashMap<String, Any>): DefaultResponse =
        remoteDataSource.signup(body)

    override suspend fun login(body: HashMap<String, Any>): DefaultResponse =
        remoteDataSource.login(body)

    override suspend fun checkId(id: String): DefaultResponse = remoteDataSource.checkId(id)
}