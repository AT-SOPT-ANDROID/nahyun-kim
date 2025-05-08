package org.sopt.at.presentation.auth.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.sopt.at.core.state.UiState
import org.sopt.at.domain.usecase.RequestSignInUseCase
import org.sopt.at.presentation.auth.signin.state.SignInState
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val requestSignInUseCase: RequestSignInUseCase
): ViewModel() {
    private val _state = MutableStateFlow(SignInState())
    val state: StateFlow<SignInState>
        get() = _state.asStateFlow()

    private val _uiState = MutableStateFlow<UiState<Unit>>(UiState.Empty)
    val uiState: StateFlow<UiState<Unit>>
        get() = _uiState.asStateFlow()

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
        viewModelScope.launch {
            val response = requestSignInUseCase.invoke(
                id = _state.value.userId,
                password = _state.value.password
            )
            if (response.success) {
                _state.value = _state.value.copy(isLoginSuccess = true)
                _uiState.value = UiState.Success(Unit)
            } else {
                _uiState.value = UiState.Error(
                    message = "아이디 또는 비밀번호가 일치하지 않습니다."
                )
            }
        }
    }

    private fun checkButtonEnabled(id: String, password: String): Boolean {
        return id.isNotEmpty() && password.isNotEmpty()
    }
}