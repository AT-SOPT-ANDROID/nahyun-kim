package org.sopt.at.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.at.core.designsystem.theme.ATSOPTANDROIDTheme
import org.sopt.at.presentation.main.MainScreen
import org.sopt.at.presentation.main.SplashViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val splashScreen = installSplashScreen()
        splashScreen.setKeepOnScreenCondition {
            !viewModel.isReady.value
        }

        setContent {
            ATSOPTANDROIDTheme {
                val isReady = viewModel.isReady.collectAsState().value

                if (isReady) {
                    MainScreen(startDestination = viewModel.getStartDestination())
                }
            }
        }
    }
}