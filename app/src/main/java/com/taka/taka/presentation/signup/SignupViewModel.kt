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
    val state = _state.distinctUntilChanged()
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

    fun signup(id: String, pwd: String) {
        // id 혹은 pwd가 빈칸인 경우 회원가 불가
        if (id.isBlank() || pwd.isBlank()) {
            _state.value = "아이디와 비밀번호를 입력해 주세요"
            _signupSuccess.value = false
            return
        }

        _signupSuccess.value = true
        TODO("서버 호출")
    }
}