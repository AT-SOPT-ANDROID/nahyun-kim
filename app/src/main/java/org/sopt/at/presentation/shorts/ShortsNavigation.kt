package org.sopt.at.presentation.shorts

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import org.sopt.at.core.navigation.MainTabRoute

fun NavController.navigateToShorts(
    navOptions: NavOptions? = null
) {
    navigate(Shorts, navOptions)
}

fun NavGraphBuilder.shortsNavGraph(
    paddingValues: PaddingValues
) {
    composable<Shorts> {
        ShortsRoute(
            paddingValues = paddingValues
        )
    }
}

@Serializable
data object Shorts : MainTabRoute