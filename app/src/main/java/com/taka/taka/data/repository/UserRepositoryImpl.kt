package com.taka.taka.data.repository

import com.taka.taka.data.datasource.local.LocalDataSource
import com.taka.taka.data.datasource.remote.RemoteDataSource
import com.taka.taka.data.datasource.remote.response.DefaultResponse
import com.taka.taka.data.datasource.remote.response.AuthResponse
import com.taka.taka.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) :
    UserRepository {
    override suspend fun signup(body: HashMap<String, Any>): AuthResponse =
        remoteDataSource.signup(body)

    override suspend fun login(body: HashMap<String, Any>): AuthResponse =
        remoteDataSource.login(body)

    override suspend fun checkId(id: String): DefaultResponse = remoteDataSource.checkId(id)

    override suspend fun setAccessToken(accessToken: String) =
        localDataSource.setAccessToken(accessToken)

    override fun getAccessToken(): String? = localDataSource.getAccessToken()

    override suspend fun setUserIdx(userIdx: Int) = localDataSource.setUserIdx(userIdx)

    override suspend fun getUserIdx(): Int? = localDataSource.getUserIdx()
}