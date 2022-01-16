package com.taka.taka.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    private val _state: MutableLiveData<String> = MutableLiveData()
    val state: LiveData<String>
        get() = _state
    private val _loginSuccess: MutableLiveData<Boolean> = MutableLiveData()
    val loginSuccess: LiveData<Boolean>
        get() = _loginSuccess

    fun login(id: String, pwd: String) {
        // id 혹은 pwd가 빈칸인 경우 로그인 불가
        if (id.isBlank() || pwd.isBlank()) {
            _state.value = "아이디와 비밀번호를 입력해 주세요"
            _loginSuccess.value = false
            return
        }

        _loginSuccess.value = true
        // TODO("서버 호출")
    }
}