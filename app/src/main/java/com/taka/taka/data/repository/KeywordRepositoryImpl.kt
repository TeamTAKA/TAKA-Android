package com.taka.taka.data.repository

import com.taka.taka.data.datasource.local.LocalDataSource
import com.taka.taka.domain.repository.KeywordRepository
import javax.inject.Inject

class KeywordRepositoryImpl @Inject constructor(private val localDataSource: LocalDataSource) : KeywordRepository{
    override suspend fun addRecentKeyword(keyword: String) {
        localDataSource.addRecentKeyword(keyword)
    }
}