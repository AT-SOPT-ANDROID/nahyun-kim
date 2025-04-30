package org.sopt.at.presentation.history

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import org.sopt.at.util.MainTabRoute

fun NavController.navigateToHistory(
    navOptions: NavOptions? = null
) {
    navigate(History, navOptions)
}

fun NavGraphBuilder.historyNavGraph(
    paddingValues: PaddingValues
) {
    composable<History> {
        HistoryRoute(
            paddingValues = paddingValues
        )
    }
}

@Serializable
data object History : MainTabRoute