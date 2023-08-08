package com.taka.taka.data.datasource.local

import javax.inject.Inject

class LocalDataSource @Inject constructor() {
    fun getAccessToken(): String? = SharedPreference.getAccessToken()

    fun setAccessToken(accessToken: String) {
        SharedPreference.setAccessToken(accessToken)
    }

    fun removeAccessToken() {
        SharedPreference.removeAccessToken()
    }

    fun getUserIdx(): Int? = SharedPreference.getUserIdx()

    fun setUserIdx(userIdx: Int) {
        SharedPreference.setUserIdx(userIdx)
    }

    fun removeUserIdx() {
        SharedPreference.removeUserIdx()
    }

    fun getUserId(): String = SharedPreference.getUserId()

    fun setUserId(userId: String) {
        SharedPreference.setUserId(userId)
    }

    fun removeUserId() {
        SharedPreference.removeUserId()
    }

    fun getRecentKeywords(): List<String> {
        return SharedPreference.getRecentKeywords()
    }

    fun addRecentKeyword(keyword: String) {
        SharedPreference.addRecentKeyword(keyword)
    }

    fun deleteRecentKeyword(keyword: String) {
        SharedPreference.deleteRecentKeyword(keyword)
    }
}