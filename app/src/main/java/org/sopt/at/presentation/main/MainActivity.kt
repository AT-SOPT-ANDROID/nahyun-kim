package org.sopt.at.presentation.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import org.sopt.at.presentation.auth.login.LoginActivity
import org.sopt.at.presentation.auth.login.LoginActivity.Companion.USER_ID_KEY
import org.sopt.at.ui.theme.ATSOPTANDROIDTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val userId = intent.getStringExtra(USER_ID_KEY).toString()

        setContent {
            ATSOPTANDROIDTheme {
                MainScreen(
                    userId = userId,
                    moveToLogin = {
                        val intent = Intent(this, LoginActivity::class.java).apply {
                            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                        }
                        startActivity(intent)
                    }
                )
            }
        }
    }
}