package org.sopt.at.presentation.auth.login.state

data class LoginState(
    val userId: String = "",
    val password: String = "",
    val isButtonEnabled: Boolean = false,
    val isLoginSuccess: Boolean = false,
)
