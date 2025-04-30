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
    id: String,
    navOptions: NavOptions? = null
) {
    navigate(My(id), navOptions)
}

fun NavGraphBuilder.myNavGraph(
    paddingValues: PaddingValues,
    onBackClick: () -> Unit,
    navigateToLogIn: () -> Unit,
) {
    composable<My> { backStackEntry ->
        MyRoute(
            paddingValues = paddingValues,
            onBackClick = onBackClick,
            onLogoutClick = navigateToLogIn
        )
    }
}

@Serializable
data class My(
    val id: String
): Route