package org.sopt.at.presentation.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.sopt.at.core.navigation.Route
import org.sopt.at.domain.usecase.GetUserNameUseCase
import org.sopt.at.presentation.auth.login.navigation.Login
import org.sopt.at.presentation.home.navigation.Home
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val getUserNameUseCase: GetUserNameUseCase
): ViewModel() {
    private val _isReady = MutableStateFlow(false)
    val isReady: StateFlow<Boolean>
        get() = _isReady.asStateFlow()

    var isAutoLoinEnable = false

    init {
        checkAuthLogin()
    }

    private fun checkAuthLogin() {
        var name = ""
        viewModelScope.launch {
            getUserNameUseCase().collect { it ->
                Log.d("SplashVM", "name: ${it.toString()}")
                name = it.toString()
                isAutoLoinEnable = (it != null)
                _isReady.value = true
            }
        }
    }

    fun getStartDestination(): Route {
        return if (isAutoLoinEnable == true) Home else Login()
    }
}