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
            id = id
        )
    }

    fun updatePassword(password: String) {
        _state.value = _state.value.copy(
            password = password
        )
    }

    fun updateNickname(nickname: String) {
        _state.value = _state.value.copy(
            nickname = nickname
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
                }
            }
            SignUpStep.PASSWORD -> {
                if (isValidPassword()) {
                    updateStep(MoveDirection.NEXT)
                    _uiState.value = UiState.Empty
                } else {
                    _uiState.value = UiState.Error(
                        message = "비밀번호 형식에 맞지 않습니다."
                    )
                }
            }
            SignUpStep.NICKNAME -> {
                if (isValidNickname()) {
                    _uiState.value = UiState.Success(Unit)
                    _state.value = _state.value.copy(isSignupSuccess = true)
                } else {
                    _uiState.value = UiState.Error(
                        message = "닉네임 형식에 맞지 않습니다."
                    )
                }
            }
        }
    }

    fun isValidId(): Boolean {
        return checkIdValidation(_state.value.id)
    }

    fun isValidPassword(): Boolean {
        return checkPasswordValidation(_state.value.password)
    }

    fun isValidNickname(): Boolean {
        return checkNicknameValidation(_state.value.nickname)
    }

    companion object {
        // 정규식 패턴
        val ID_PATTERN = "^(?=.*[a-z]+)(?=.*\\d*)[a-z\\d가-힣]{6,12}$".toRegex() // 영문 소문자(필수), 숫자(선택) 조합 6~12자
        val PWD_PATTERN = "^.*(?=^.{8,15}$)(?=.*\\d)(?=.*[a-zA-Z])(?=.*[~!@#$%^&*]).*$".toRegex() // 영문, 숫자, 특수문자(~!@#$%^&*) 조합 8~15자
        val NICKNAME_PATTERN = "^(?=.*[가-힣]*)(?=.*[a-zA-Z]*)(?=.*\\d*)[가-힣a-zA-Z\\d]{1,20}$".toRegex() // 한글/영어/숫자 1~20자

        fun checkIdValidation(id: String): Boolean {
            return id.matches(ID_PATTERN)
        }

        fun checkPasswordValidation(pwd: String): Boolean {
            return pwd.matches(PWD_PATTERN)
        }

        fun checkNicknameValidation(nickname: String): Boolean {
            return nickname.matches(NICKNAME_PATTERN)
        }
    }
}

enum class MoveDirection() {
    PREV, NEXT
}

enum class SignUpStep(var order: Int) {
    ID(1),
    PASSWORD(2),
    NICKNAME(3);

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