package org.sopt.at.presentation.auth.signup.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import org.sopt.at.presentation.auth.login.navigation.Login
import org.sopt.at.presentation.auth.signup.SignUpRoute
import org.sopt.at.util.Route

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
            onBackClick = navigateBack,
            onLoginClick = navigateToLogin,
        )
    }
}

@Serializable
data object SignUp : Route