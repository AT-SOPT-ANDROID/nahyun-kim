package org.sopt.at.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.sopt.at.core.navigation.Route
import org.sopt.at.domain.usecase.GetAutoLoginEnableUseCase
import org.sopt.at.presentation.auth.signin.navigation.SignIn
import org.sopt.at.presentation.home.navigation.Home
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val getAutoLoginEnableUseCase: GetAutoLoginEnableUseCase
): ViewModel() {
    private val _isReady = MutableStateFlow(false)
    val isReady: StateFlow<Boolean>
        get() = _isReady.asStateFlow()

    var isAutoLoginEnable = false

    init {
        checkAuthLogin()
    }

    private fun checkAuthLogin() {
        viewModelScope.launch {
            isAutoLoginEnable = getAutoLoginEnableUseCase.invoke()
            _isReady.value = true
        }
    }

    fun getStartDestination(): Route {
        return if (isAutoLoginEnable == true) Home else SignIn
    }
}