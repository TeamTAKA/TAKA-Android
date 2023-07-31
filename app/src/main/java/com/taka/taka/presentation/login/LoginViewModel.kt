package com.taka.taka.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.taka.taka.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _state: MutableLiveData<String> = MutableLiveData()
    val state: LiveData<String> get() = _state
    private val _loginSuccess: MutableLiveData<Boolean> = MutableLiveData()
    val loginSuccess: LiveData<Boolean>
        get() = _loginSuccess

    fun login(id: String, pwd: String) {
        // id 혹은 pwd가 빈칸인 경우 로그인 불가
        if (id.isBlank() || pwd.isBlank()) {
            _state.value = "아이디와 비밀번호를 입력해 주세요"
        } else {
            viewModelScope.launch {
                try {
                    val body = hashMapOf<String, Any>()
                    body["id"] = id
                    body["password"] = pwd

                    val response = userRepository.login(body)

                    _loginSuccess.value = response.success

                    if (response.success) {
                        userRepository.setAccessToken(response.data.accessToken)
                        userRepository.setUserIdx(response.data.userIdx)
                        userRepository.setUserId(id)
                    } else {
                        _state.value = response.message
                    }
                } catch (e: Exception) {
                    _state.value = "로그인에 실패했습니다"
                    e.printStackTrace()
                }
            }
        }
    }
}