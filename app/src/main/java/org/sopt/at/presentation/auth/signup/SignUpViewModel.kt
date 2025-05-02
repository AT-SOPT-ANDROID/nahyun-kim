package org.sopt.at.presentation.auth.signup

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.sopt.at.core.state.UiState
import org.sopt.at.presentation.auth.signup.state.SignUpState

class SignUpViewModel: ViewModel() {

    private val _state = MutableStateFlow(SignUpState())
    val state: StateFlow<SignUpState>
        get() = _state.asStateFlow()

    private val _uiState = MutableStateFlow<UiState<Unit>>(UiState.Empty)
    val uiState: StateFlow<UiState<Unit>>
        get() = _uiState.asStateFlow()

    fun updateStep(moveDirection: MoveDirection) {
        val currentStep = _state.value.currentStep
        val targetStep = when (moveDirection) {
            MoveDirection.PREV -> SignUpStep.getPrevStep(currentStep)
            MoveDirection.NEXT -> SignUpStep.getNextStep(currentStep)
        }
        if (targetStep == null) return
        _state.value = _state.value.copy(
            currentStep = targetStep
        )
    }

    fun updateId(id: String) {
        _state.value = _state.value.copy(
            userId = id
        )
    }

    fun updatePassword(password: String) {
        _state.value = _state.value.copy(
            password = password
        )
    }

    fun onNextClick() {
        when (_state.value.currentStep) {
            SignUpStep.ID -> {
                if (isValidId()) {
                    updateStep(MoveDirection.NEXT)
                    _uiState.value = UiState.Empty
                } else {
                    _uiState.value = UiState.Error(
                        message = "아이디 형식에 맞지 않습니다."
                    )
                    _state.value = _state.value.copy(isSignupSuccess = true)
                }
            }
            SignUpStep.PASSWORD -> {
                if (isValidPassword()) {
                    _uiState.value = UiState.Success(Unit)
                    _state.value = _state.value.copy(isSignupSuccess = true)
                } else {
                    _uiState.value = UiState.Error(
                        message = "비밀번호 형식에 맞지 않습니다."
                    )
                    _state.value = _state.value.copy(isSignupSuccess = true)
                }
            }
        }
    }

    fun isValidId(): Boolean {
        return checkIdValidation(_state.value.userId)
    }

    fun isValidPassword(): Boolean {
        return checkPasswordValidation(_state.value.password)
    }

    companion object {
        // 정규식 패턴
        val ID_PATTERN = "^(?=.*[a-z]+)(?=.*\\d*)[a-z\\d]{6,12}$".toRegex()
        val PWD_PATTERN = "^.*(?=^.{8,15}$)(?=.*\\d)(?=.*[a-zA-Z])(?=.*[~!@#$%^&*]).*$".toRegex()

        fun checkIdValidation(id: String): Boolean {
            return id.matches(ID_PATTERN)
        }

        fun checkPasswordValidation(pwd: String): Boolean {
            return pwd.matches(PWD_PATTERN)
        }
    }
}

enum class MoveDirection() {
    PREV, NEXT
}

enum class SignUpStep(var order: Int) {
    ID(1),
    PASSWORD(2);

    companion object {
        fun getPrevStep(currentStep: SignUpStep): SignUpStep? { // 뒤로가기
            return entries.find {
                it.order == currentStep.order - 1
            }
        }

        fun getNextStep(currentStep: SignUpStep): SignUpStep? { // 다음
            return entries.find {
                it.order == currentStep.order + 1
            }
        }
    }
}