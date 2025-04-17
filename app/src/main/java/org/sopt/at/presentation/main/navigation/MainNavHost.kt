package org.sopt.at.presentation.main.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.sopt.at.presentation.history.History
import org.sopt.at.presentation.history.HistoryScreen
import org.sopt.at.presentation.home.Home
import org.sopt.at.presentation.home.HomeScreen
import org.sopt.at.presentation.live.Live
import org.sopt.at.presentation.live.LiveScreen
import org.sopt.at.presentation.search.Search
import org.sopt.at.presentation.search.SearchScreen
import org.sopt.at.presentation.shorts.Shorts
import org.sopt.at.presentation.shorts.ShortsScreen

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