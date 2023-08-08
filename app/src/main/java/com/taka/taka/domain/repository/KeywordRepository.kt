package com.taka.taka.domain.repository

interface KeywordRepository {
    suspend fun getRecentKeywords(): List<String>
    suspend fun addRecentKeyword(keyword: String)
    suspend fun deleteRecentKeyword(keyword: String)
}