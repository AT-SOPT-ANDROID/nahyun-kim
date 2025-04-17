package org.sopt.at.ui.screens.login

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.sopt.at.model.LoginUser

class LoginViewModel: ViewModel() {
    private val _loginUserInfo = MutableStateFlow<LoginUser>(LoginUser())
    val loginUserInfo: StateFlow<LoginUser>
        get() = _loginUserInfo.asStateFlow()

    private val _isButtonEnable = MutableStateFlow(false)
    val isButtonEnable: StateFlow<Boolean>
        get() = _isButtonEnable.asStateFlow()

    // 회원가입 시 받아온 유저 정보
    private val _registerUserInfo = MutableStateFlow<LoginUser?>(null)
    val registerUserInfo: StateFlow<LoginUser?>
        get() = _registerUserInfo.asStateFlow()

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

    fun setRegisterUserInfo(user: LoginUser?) {
        _registerUserInfo.value = user
    }

    private fun updateButtonState() {
        val (id, password) = _loginUserInfo.value
        _isButtonEnable.value = id.isNotEmpty() && password.isNotEmpty()
    }

    // 로그인 한 유저 정보가 회원가입 한 유저 정보와 동일한지 판단
    fun isIdenticalUser(): Boolean? {
        if (_registerUserInfo.value == null) return null
        return _registerUserInfo.value == _loginUserInfo.value
    }
}