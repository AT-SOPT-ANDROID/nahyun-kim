package org.sopt.at.presentation.home.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import org.sopt.at.core.navigation.MainTabRoute
import org.sopt.at.presentation.home.HomeRoute

fun NavController.navigateToHome(
    navOptions: NavOptions? = null
) {
    navigate(Home, navOptions)
}

fun NavGraphBuilder.homeNavGraph(
    paddingValues: PaddingValues,
    navigateToMy: () -> Unit
) {
    composable<Home> {
        HomeRoute(
            paddingValues = paddingValues,
            navigateToMy = navigateToMy,
        )
    }
}

@Serializable
data object Home: MainTabRoute