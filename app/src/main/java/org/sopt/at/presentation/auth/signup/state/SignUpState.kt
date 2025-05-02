package org.sopt.at.presentation.auth.signup.state

import org.sopt.at.presentation.auth.signup.SignUpStep

data class SignUpState(
    val userId: String = "",
    val password: String = "",
    val currentStep: SignUpStep = SignUpStep.ID,
    val isSignupSuccess: Boolean = false
)