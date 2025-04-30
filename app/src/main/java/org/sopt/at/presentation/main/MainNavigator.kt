package org.sopt.at.presentation.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import org.sopt.at.presentation.auth.login.navigation.Login
import org.sopt.at.presentation.auth.login.navigation.navigateToLogin
import org.sopt.at.presentation.auth.signup.navigation.navigateToSignUp
import org.sopt.at.presentation.history.navigateToHistory
import org.sopt.at.presentation.home.navigation.Home
import org.sopt.at.presentation.home.navigation.navigateToHome
import org.sopt.at.presentation.live.navigateToLive
import org.sopt.at.presentation.my.navigation.navigateToMy
import org.sopt.at.presentation.search.navigateToSearch
import org.sopt.at.presentation.shorts.navigateToShorts

class MainNavigator(
    val navController: NavHostController
) {
    private val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val startDestination = Home

    val currentTab: MainNavTab?
        @Composable get() = MainNavTab.find { tab ->
            currentDestination?.hasRoute(tab::class) == true
        }

    fun navigate(tab: MainNavTab) {
        val navOptions = navOptions {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }

        when (tab) {
            MainNavTab.HOME -> {
                navController.popBackStack(MainNavTab.HOME.route, inclusive = true)
                navController.navigate(MainNavTab.HOME.route, navOptions)
            }

            MainNavTab.SHORTS -> navController.navigateToShorts(navOptions)
            MainNavTab.LIVE -> navController.navigateToLive(navOptions)
            MainNavTab.SEARCH -> navController.navigateToSearch(navOptions)
            MainNavTab.HISTORY -> navController.navigateToHistory(navOptions)
        }
    }

    @Composable
    fun setBottomBarVisibility() = MainNavTab.contains {
        currentDestination?.hasRoute(it::class) == true
    }

    fun navigateBack() {
        navController.popBackStack()
    }

    fun navigateToLogin(loginInfo: Login? = null) {
        navController.navigateToLogin(
            loginInfo,
            navOptions {
                popUpTo(navController.graph.id) {
                    inclusive = true
                }
            }
        )
    }

    fun navigateToSignUp() {
        navController.navigateToSignUp()
    }

    fun navigateToHome() {
        navController.navigateToHome(
            navOptions {
                popUpTo(navController.graph.id) {
                    inclusive = true
                }
            }
        )
    }

    fun navigateToMy() {
        navController.navigateToMy()
    }
}

@Composable
internal fun rememberMainNavigator(
    navController: NavHostController = rememberNavController()
): MainNavigator = remember(navController) {
    MainNavigator(navController)
}