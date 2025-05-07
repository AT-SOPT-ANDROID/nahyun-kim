package org.sopt.at.presentation.auth.signin

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.sopt.at.core.state.UiState
import org.sopt.at.domain.usecase.SaveUserInfoUseCase
import org.sopt.at.presentation.auth.signin.navigation.SignIn
import org.sopt.at.presentation.auth.signin.state.SignInState
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val saveUserInfoUseCase: SaveUserInfoUseCase
): ViewModel() {
    private val _state = MutableStateFlow(SignInState())
    val state: StateFlow<SignInState>
        get() = _state.asStateFlow()

    private val _uiState = MutableStateFlow<UiState<Unit>>(UiState.Empty)
    val uiState: StateFlow<UiState<Unit>>
        get() = _uiState.asStateFlow()

    val registerUserInfo = savedStateHandle.toRoute<SignIn>() // 회원가입 정보

    fun updateId(id: String) {
        val newState = _state.value.copy(
            userId = id,
        )
        _state.value = newState.copy(
            isButtonEnabled = checkButtonEnabled(newState.userId, newState.password)
        )
    }

    fun updatePassword(password: String) {
        val newState = _state.value.copy(
            password = password,
        )
        _state.value = newState.copy(
            isButtonEnabled = checkButtonEnabled(newState.userId, newState.password)
        )
    }

    fun tryLogin() {
        when (checkIdenticalUser()) {
            true -> { // 성공
                _state.value = _state.value.copy(isLoginSuccess = true)
                _uiState.value = UiState.Success(Unit)
                saveUser()
            }
            false -> { // 실패
                _uiState.value = UiState.Error(
                    message = "아이디 또는 비밀번호가 일치하지 않습니다."
                )
            }
            null -> {
                _uiState.value = UiState.Error(
                    "회원 정보가 없습니다."
                )
            }
        }
    }

    // 저장소에 로그인 정보 저장
    fun saveUser() {
        viewModelScope.launch {
            saveUserInfoUseCase(
                id = _state.value.userId,
                password = _state.value.password
            )
        }
    }

    private fun checkButtonEnabled(id: String, password: String): Boolean {
        return id.isNotEmpty() && password.isNotEmpty()
    }

    // 로그인 한 유저 정보가 회원가입 한 유저 정보와 동일한지 판단
    fun checkIdenticalUser(): Boolean? {
        val (id, password) = _state.value
        if (registerUserInfo.id.isEmpty()) return null
        return registerUserInfo.id == id
                && registerUserInfo.password == password
    }
}