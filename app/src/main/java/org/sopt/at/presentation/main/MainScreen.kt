package org.sopt.at.presentation.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import org.sopt.at.core.navigation.Route
import org.sopt.at.presentation.auth.login.navigation.loginNavGraph
import org.sopt.at.presentation.auth.signup.navigation.signUpNavGraph
import org.sopt.at.presentation.history.historyNavGraph
import org.sopt.at.presentation.home.navigation.homeNavGraph
import org.sopt.at.presentation.live.liveNavGraph
import org.sopt.at.presentation.main.component.MainBottomBar
import org.sopt.at.presentation.my.navigation.myNavGraph
import org.sopt.at.presentation.search.searchNavGraph
import org.sopt.at.presentation.shorts.shortsNavGraph


@Composable
fun MainScreen(
    startDestination: Route,
    navigator: MainNavigator = rememberMainNavigator()
) {
    val snackbarHostState = remember { SnackbarHostState() }

    navigator.initStartDestination(startDestination)

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding(),
        bottomBar = {
            MainBottomBar(
                visible = navigator.setBottomBarVisibility(),
                currentTab = navigator.currentTab,
                onTabSelected = navigator::navigate,
            )
        },
    ) { innerPadding ->
        NavHost(
            navController = navigator.navController,
            startDestination = navigator.startDestination
        ) {
            loginNavGraph(
                paddingValues = innerPadding,
                navigateBack = navigator::navigateBack,
                navigateToHome = navigator::navigateToHome,
                navigateToSignUp = navigator::navigateToSignUp,
                snackbarHostState = snackbarHostState
            )
            signUpNavGraph(
                paddingValues = innerPadding,
                navigateBack = navigator::navigateBack,
                navigateToLogin = navigator::navigateToLogin
            )
            homeNavGraph(
                paddingValues = innerPadding,
                navigateToMy = navigator::navigateToMy
            )
            shortsNavGraph(innerPadding)
            liveNavGraph(innerPadding)
            searchNavGraph(innerPadding)
            historyNavGraph(innerPadding)

            myNavGraph(
                paddingValues = innerPadding,
                onBackClick = navigator::navigateBack,
                navigateToLogIn = navigator::navigateToLogin
            )
        }
    }
}