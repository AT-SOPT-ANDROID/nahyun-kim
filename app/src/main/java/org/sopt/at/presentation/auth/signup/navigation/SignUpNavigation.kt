package org.sopt.at.presentation.auth.signup.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import org.sopt.at.core.navigation.Route
import org.sopt.at.presentation.auth.login.navigation.Login
import org.sopt.at.presentation.auth.signup.SignUpRoute

fun NavController.navigateToSignUp(
    navOptions: NavOptions? = null
) {
    navigate(SignUp, navOptions)
}

fun NavGraphBuilder.signUpNavGraph(
    paddingValues: PaddingValues,
    navigateBack: () -> Unit,
    navigateToLogin: (Login) -> Unit,
) {
    composable<SignUp> {
        SignUpRoute(
            paddingValues = paddingValues,
            navigateBack = navigateBack,
            navigateToLogin = navigateToLogin,
        )
    }
}

@Serializable
data object SignUp : Route