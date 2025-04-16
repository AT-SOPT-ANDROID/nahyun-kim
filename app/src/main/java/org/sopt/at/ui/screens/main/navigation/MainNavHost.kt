package org.sopt.at.ui.screens.main.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.sopt.at.ui.screens.main.History
import org.sopt.at.ui.screens.main.HistoryScreen
import org.sopt.at.ui.screens.main.Home
import org.sopt.at.ui.screens.main.HomeScreen
import org.sopt.at.ui.screens.main.Live
import org.sopt.at.ui.screens.main.LiveScreen
import org.sopt.at.ui.screens.main.Search
import org.sopt.at.ui.screens.main.SearchScreen
import org.sopt.at.ui.screens.main.Shorts
import org.sopt.at.ui.screens.main.ShortsScreen

@Composable
fun MainNavHost(
    paddingValues: PaddingValues,
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Home
    ) {
        composable<Home>{
            HomeScreen(paddingValues)
        }
        composable<Shorts>{
            ShortsScreen(paddingValues)
        }
        composable<Live>{
            LiveScreen(paddingValues)
        }
        composable<Search>{
            SearchScreen(paddingValues)
        }
        composable<History> {
            HistoryScreen(paddingValues)
        }
    }
}