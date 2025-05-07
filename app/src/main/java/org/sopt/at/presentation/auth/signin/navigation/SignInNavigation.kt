package org.sopt.at.presentation.auth.signin.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.SnackbarHostState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import org.sopt.at.core.navigation.Route
import org.sopt.at.presentation.auth.signin.SignInRoute

fun NavController.navigateToSignIn(
    userInfo: SignIn?,
    navOptions: NavOptions? = null
) {
    navigate(userInfo ?: SignIn(), navOptions)
}

fun NavGraphBuilder.signInNavGraph(
    paddingValues: PaddingValues,
    navigateBack: () -> Unit,
    navigateToHome: () -> Unit,
    navigateToSignUp: () -> Unit,
    snackbarHostState: SnackbarHostState
) {
    composable<SignIn> {
        SignInRoute(
            paddingValues = paddingValues,
            navigateBack = navigateBack,
            navigateToHome = navigateToHome,
            navigateToSignUp = navigateToSignUp,
            snackbarHostState = snackbarHostState
        )
    }
}

@Serializable
data class SignIn(
    val id: String = "",
    val password: String = ""
) : Route