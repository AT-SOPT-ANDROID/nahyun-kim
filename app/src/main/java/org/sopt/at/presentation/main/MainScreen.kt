package org.sopt.at.presentation.main

import android.app.Activity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.sopt.at.presentation.history.History
import org.sopt.at.presentation.history.HistoryScreen
import org.sopt.at.presentation.home.Home
import org.sopt.at.presentation.home.HomeScreen
import org.sopt.at.presentation.home.my.My
import org.sopt.at.presentation.home.my.MyScreen
import org.sopt.at.presentation.live.Live
import org.sopt.at.presentation.live.LiveScreen
import org.sopt.at.presentation.main.navigation.MainBottomBar
import org.sopt.at.presentation.search.Search
import org.sopt.at.presentation.search.SearchScreen
import org.sopt.at.presentation.shorts.Shorts
import org.sopt.at.presentation.shorts.ShortsScreen


@Composable
fun MainScreen(
    userId: String,
    moveToLogin: () -> Unit
) {
    val navController = rememberNavController()
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding(),
        bottomBar = {
            MainBottomBar(navController)
        },
    ) { innerPadding ->
        val context = LocalContext.current
        NavHost(
            navController = navController,
            startDestination = Home
        ) {
            composable<Home>{ backStackEntry ->
                HomeScreen(
                    paddingValues = innerPadding,
                    userId = userId,
                    navigationToMy = { id ->
                        navController.navigate(My(id))
                    }
                )
            }
            composable<Shorts>{
                ShortsScreen(paddingValues = innerPadding)
            }
            composable<Live>{
                LiveScreen(paddingValues = innerPadding)
            }
            composable<Search>{
                SearchScreen(paddingValues = innerPadding)
            }
            composable<History> {
                HistoryScreen(paddingValues = innerPadding)
            }

            composable<My> {
                MyScreen(
                    paddingValues = innerPadding,
                    onBackClick = { (context as? Activity)?.onBackPressed() },
                    onLogoutClick = moveToLogin
                )
            }
        }
    }
}