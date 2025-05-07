package org.sopt.at.presentation.auth.login.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.SnackbarHostState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import org.sopt.at.core.navigation.Route
import org.sopt.at.presentation.auth.login.LoginRoute

fun NavController.navigateToLogin(
    userInfo: Login?,
    navOptions: NavOptions? = null
) {
    navigate(userInfo ?: Login(), navOptions)
}

fun NavGraphBuilder.loginNavGraph(
    paddingValues: PaddingValues,
    navigateBack: () -> Unit,
    navigateToHome: () -> Unit,
    navigateToSignUp: () -> Unit,
    snackbarHostState: SnackbarHostState
) {
    composable<Login> {
        LoginRoute(
            paddingValues = paddingValues,
            navigateBack = navigateBack,
            navigateToHome = navigateToHome,
            navigateToSignUp = navigateToSignUp,
            snackbarHostState = snackbarHostState
        )
    }
}

@Serializable
data class Login(
    val id: String = "",
    val password: String = ""
) : Route