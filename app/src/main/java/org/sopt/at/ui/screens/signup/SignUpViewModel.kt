package org.sopt.at.ui.screens.signup

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.sopt.at.model.LoginUser

class SignUpViewModel: ViewModel() {

    private val _currentStep = MutableStateFlow(SignUpStep.ID)
    val currentStep: StateFlow<SignUpStep>
        get() = _currentStep.asStateFlow()

    private val _userInfo = MutableStateFlow<LoginUser>(LoginUser(id = "", password = ""))
    val userInfo: StateFlow<LoginUser>
        get() = _userInfo.asStateFlow()

    fun updateStep(step: SignUpStep) {
        _currentStep.value = step
    }

    fun updateId(id: String) {
        _userInfo.update {
            it.copy(id = id)
        }
    }

    fun updatePassword(password: String) {
        _userInfo.update {
            it.copy(password = password)
        }
    }

    fun isValidId(): Boolean {
        return checkIdValidation(_userInfo.value.id)
    }

    fun isValidPassword(): Boolean {
        return checkPasswordValidation(_userInfo.value.password)
    }

    companion object {
        // 정규식 패턴
        const val ID_PATTERN = "^(?=.*[a-z]+)(?=.*\\d*)[a-z\\d]{6,12}$"
        const val PWD_PATTERN = "^.*(?=^.{8,15}$)(?=.*\\d)(?=.*[a-zA-Z])(?=.*[~!@#$%^&*]).*$"

        fun checkIdValidation(id: String): Boolean {
            return id.matches(ID_PATTERN.toRegex())
        }

        fun checkPasswordValidation(pwd: String): Boolean {
            return pwd.matches(PWD_PATTERN.toRegex())
        }
    }
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
    }
}