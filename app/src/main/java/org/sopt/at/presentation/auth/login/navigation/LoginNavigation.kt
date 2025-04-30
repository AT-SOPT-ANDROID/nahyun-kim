package org.sopt.at.presentation.auth.login.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.SnackbarHostState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import org.sopt.at.presentation.auth.login.LoginRoute
import org.sopt.at.util.Route

fun NavController.navigateToLogin(
    userInfo: Login?,
    navOptions: NavOptions? = null
) {
    navigate(userInfo ?: Login(), navOptions)
}

fun NavGraphBuilder.loginNavGraph(
    paddingValues: PaddingValues,
    navigateBack: () -> Unit,
    navigateToHome: (String) -> Unit,
    navigateToSignUp: () -> Unit,
    snackbarHostState: SnackbarHostState
) {
    composable<Login> {
        LoginRoute(
            paddingValues = paddingValues,
            onBackClick = navigateBack,
            onLoginClick = navigateToHome,
            onSignUpClick = navigateToSignUp,
            snackbarHostState = snackbarHostState
        )
    }
}

@Serializable
data class Login(
    val id: String = "",
    val password: String = ""
) : Route