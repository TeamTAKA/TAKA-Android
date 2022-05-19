package com.taka.taka.presentation.signup

import androidx.lifecycle.*
import com.taka.taka.domain.repository.TicketRepository
import com.taka.taka.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val ticketRepository: TicketRepository
) : ViewModel() {

    private val _state = MutableLiveData<String>()
    val state: LiveData<String> get() = _state
    private val _isChecked = MutableLiveData<Boolean>()
    val isChecked = _isChecked.distinctUntilChanged()
    private val _signupSuccess = MutableLiveData<Boolean>()
    val signupSuccess = _signupSuccess.distinctUntilChanged()

    fun checkId(id: String) {
        viewModelScope.launch {
            try {
                val response = userRepository.checkId(id)
                _isChecked.value = response.success

                if (!response.success) {
                    _state.value = response.message
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun setIdUnchecked() {
        _isChecked.value = false
    }

    fun signup(id: String, pwd: String, pwdCheck: String) {
        // id 혹은 pwd가 빈칸인 경우 회원가 불가
        if (id.isBlank() || pwd.isBlank()) {
            _state.value = "아이디와 비밀번호를 입력해 주세요"
        } else if (isChecked.value != true) {
            _state.value = "아이디 중복확인을 해주세요"
        } else if (pwd != pwdCheck) {
            _state.value = "비밀번호 확인을 다시 한 번 입력해주세요"
        } else {
            viewModelScope.launch {
                try {
                    val body = hashMapOf<String, Any>()
                    body["id"] = id
                    body["password"] = pwd

                    val response = userRepository.signup(body)

                    _signupSuccess.value = response.success

                    if (response.success) {
                        userRepository.setAccessToken(response.data.accessToken)
                        userRepository.setUserIdx(response.data.userIdx)
                    } else {
                        _state.value = response.message
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    _state.value = "회원가입에 실패했습니다"
                }
            }
        }
    }
}