package org.sopt.at.presentation.auth.signin.state

data class SignInState(
    val id: String = "",
    val password: String = "",
    val isButtonEnabled: Boolean = false,
    val isLoginSuccess: Boolean = false,
)
