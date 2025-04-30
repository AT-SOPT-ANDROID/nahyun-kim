package org.sopt.at.presentation.live

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import org.sopt.at.presentation.history.HistoryRoute
import org.sopt.at.util.MainTabRoute

fun NavController.navigateToLive(
    navOptions: NavOptions? = null
) {
    navigate(Live, navOptions)
}

fun NavGraphBuilder.liveNavGraph(
    paddingValues: PaddingValues
) {
    composable<Live> {
        HistoryRoute(
            paddingValues = paddingValues
        )
    }
}

@Serializable
data object Live : MainTabRoute