package org.sopt.at.presentation.auth.signup.state

import org.sopt.at.presentation.auth.signup.SignUpStep

data class SignUpState(
    val id: String = "",
    val password: String = "",
    val nickname: String = "",
    val currentStep: SignUpStep = SignUpStep.ID,
    val isSignupSuccess: Boolean = false
)