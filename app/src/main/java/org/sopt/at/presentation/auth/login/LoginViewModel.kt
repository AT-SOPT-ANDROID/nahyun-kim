package org.sopt.at.presentation.auth.login

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.sopt.at.domain.model.UserInfo
import org.sopt.at.domain.usecase.SaveUserInfoUseCase
import org.sopt.at.presentation.auth.login.navigation.Login
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val saveUserInfoUseCase: SaveUserInfoUseCase
): ViewModel() {
    private val _loginUserInfo = MutableStateFlow<UserInfo>(UserInfo())
    val loginUserInfo: StateFlow<UserInfo>
        get() = _loginUserInfo.asStateFlow()

    private val _isButtonEnable = MutableStateFlow(false)
    val isButtonEnable: StateFlow<Boolean>
        get() = _isButtonEnable.asStateFlow()

    val registerUserInfo = savedStateHandle.toRoute<Login>()

    private val _autoLoinEnable = MutableStateFlow(false)

    fun updateId(id: String) {
        _loginUserInfo.update {
            it.copy(id = id)
        }
        updateButtonState()
    }

    fun saveUser() {
        viewModelScope.launch {
            saveUserInfoUseCase(
                id = _loginUserInfo.value.id,
                password = _loginUserInfo.value.password
            )
        }
    }

    fun updatePassword(password: String) {
        _loginUserInfo.update {
            it.copy(password = password)
        }
        updateButtonState()
    }

    private fun updateButtonState() {
        val (id, password) = _loginUserInfo.value
        _isButtonEnable.value = id.isNotEmpty() && password.isNotEmpty()
    }

    // 로그인 한 유저 정보가 회원가입 한 유저 정보와 동일한지 판단
    fun isIdenticalUser(): Boolean? {
        if (registerUserInfo.id.isEmpty()) return null
        return registerUserInfo.id == _loginUserInfo.value.id
                && registerUserInfo.password == _loginUserInfo.value.password
    }
}