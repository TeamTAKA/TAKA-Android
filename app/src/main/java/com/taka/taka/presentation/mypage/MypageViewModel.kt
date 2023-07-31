package com.taka.taka.presentation.mypage

import androidx.lifecycle.ViewModel
import com.taka.taka.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MypageViewModel @Inject constructor(
    private val userRepository: UserRepository,
) : ViewModel() {
    val userId: String = userRepository.getUserId()

    suspend fun logout() {
        userRepository.removeAccessToken()
        userRepository.removeUserIdx()
        userRepository.removeUserId()
    }
}