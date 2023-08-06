package com.taka.taka.domain.repository

interface KeywordRepository {
    suspend fun addRecentKeyword(keyword: String)
}