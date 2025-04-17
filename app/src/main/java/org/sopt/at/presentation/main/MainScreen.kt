package org.sopt.at.presentation.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import org.sopt.at.presentation.main.navigation.MainBottomBar
import org.sopt.at.presentation.main.navigation.MainNavHost


@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding(),
        bottomBar = {
            MainBottomBar(navController)
        },
    ) { innerPadding ->
        MainNavHost(
            innerPadding,
            navController
        )
    }
}