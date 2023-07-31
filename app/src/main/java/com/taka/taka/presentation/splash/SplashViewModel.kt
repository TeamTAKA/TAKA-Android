package com.taka.taka.presentation.splash

import androidx.lifecycle.ViewModel
import com.taka.taka.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    userRepository: UserRepository
) : ViewModel() {
    val isAutoLoginEnabled = userRepository.getAccessToken() != null
}