package com.taka.taka.data.datasource.local

import javax.inject.Inject

class LocalDataSource @Inject constructor() {
    fun getAccessToken(): String? = SharedPreference.getAccessToken()

    fun setAccessToken(accessToken: String) {
        SharedPreference.setAccessToken(accessToken)
    }

    fun getUserIdx(): Int? = SharedPreference.getUserIdx()

    fun setUserIdx(userIdx: Int) {
        SharedPreference.setUserIdx(userIdx)
    }
}