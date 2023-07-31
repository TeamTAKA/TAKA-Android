package com.taka.taka.data.datasource.local

import android.app.Application
import android.content.Context
import android.content.SharedPreferences

object SharedPreference {
    private var pref: SharedPreferences? = null

    fun getInstance(context: Context): SharedPreference {
        if (pref == null) {
            pref = context.getSharedPreferences(
                "sharedPreferences",
                Application.MODE_PRIVATE
            )
        }
        return this
    }

    // 로그인 토큰
    private const val ACCESS_TOKEN = "ACCESS_TOKEN"
    fun getAccessToken(): String? = pref?.getString(ACCESS_TOKEN, null)
    fun setAccessToken(accessToken: String) =
        pref?.edit()?.putString(ACCESS_TOKEN, accessToken)?.apply()
    fun removeAccessToken() {
        pref?.edit()?.remove(ACCESS_TOKEN)?.apply()
    }

    // 로그인 모드
    private const val USER_IDX = "USER_IDX"
    fun getUserIdx(): Int? = pref?.getInt(USER_IDX, -1)
    fun setUserIdx(userIdx: Int) = pref?.edit()?.putInt(USER_IDX, userIdx)?.apply()
    fun removeUserIdx() {
        pref?.edit()?.remove(USER_IDX)?.apply()
    }

    // 사용자 아이디
    private const val USER_ID = "USER_ID"
    fun getUserId(): String = pref?.getString(USER_ID, "") ?: ""
    fun setUserId(userId: String) = pref?.edit()?.putString(USER_ID, userId)?.apply()
    fun removeUserId() {
        pref?.edit()?.remove(USER_ID)?.apply()
    }
}