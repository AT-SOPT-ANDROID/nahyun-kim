package org.sopt.at.presentation.auth.login

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.sopt.at.presentation.auth.login.navigation.Login

class LoginViewModel(
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private val _loginUserInfo = MutableStateFlow<Login>(Login())
    val loginUserInfo: StateFlow<Login>
        get() = _loginUserInfo.asStateFlow()

    private val _isButtonEnable = MutableStateFlow(false)
    val isButtonEnable: StateFlow<Boolean>
        get() = _isButtonEnable.asStateFlow()

    val registerUserInfo = savedStateHandle.toRoute<Login>()

    fun updateId(id: String) {
        _loginUserInfo.update {
            it.copy(id = id)
        }
        updateButtonState()
    }

    fun updatePassword(password: String) {
        _loginUserInfo.update {
            it.copy(password = password)
        }
        updateButtonState()
    }

    private fun updateButtonState() {
        val (id, password) = _loginUserInfo.value
        _isButtonEnable.value = !id.isNullOrEmpty() && !password.isNullOrEmpty()
    }

    // 로그인 한 유저 정보가 회원가입 한 유저 정보와 동일한지 판단
    fun isIdenticalUser(): Boolean? {
        if (registerUserInfo.id.isEmpty()) return null
        return registerUserInfo == _loginUserInfo.value
    }
}