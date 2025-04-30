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
    id: String,
    navOptions: NavOptions? = null
) {
    navigate(Home(id), navOptions)
}

fun NavGraphBuilder.homeNavGraph(
    paddingValues: PaddingValues,
    navigateToMy: (String) -> Unit
) {
    composable<Home> {
        HomeRoute(
            paddingValues = paddingValues,
            onProfileClick = navigateToMy,
        )
    }
}

//TODO: 로그인 정보 로컬 저장 후 로직 변경
@Serializable
data class Home(
    val id: String? = null
): MainTabRoute