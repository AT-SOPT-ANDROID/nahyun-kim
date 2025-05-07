package org.sopt.at.presentation.auth.signin.state

data class SignInState(
    val userId: String = "",
    val password: String = "",
    val isButtonEnabled: Boolean = false,
    val isLoginSuccess: Boolean = false,
)
