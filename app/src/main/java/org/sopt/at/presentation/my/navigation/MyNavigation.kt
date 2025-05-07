package org.sopt.at.presentation.my.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import org.sopt.at.core.navigation.Route
import org.sopt.at.presentation.my.MyRoute

fun NavController.navigateToMy(
    navOptions: NavOptions? = null
) {
    navigate(My, navOptions)
}

fun NavGraphBuilder.myNavGraph(
    paddingValues: PaddingValues,
    onBackClick: () -> Unit,
    navigateToSignIn: () -> Unit,
) {
    composable<My> { backStackEntry ->
        MyRoute(
            paddingValues = paddingValues,
            navigateBack = onBackClick,
            navigateToSignIn = navigateToSignIn
        )
    }
}

@Serializable
data object My: Route