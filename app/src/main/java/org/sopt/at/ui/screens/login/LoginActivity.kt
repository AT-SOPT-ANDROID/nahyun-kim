@file:Suppress("UNCHECKED_CAST")

package org.sopt.at.ui.screens.login

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch
import org.sopt.at.model.LoginUser
import org.sopt.at.ui.screens.main.MainActivity
import org.sopt.at.ui.screens.signup.SignupActivity
import org.sopt.at.ui.theme.ATSOPTANDROIDTheme


class LoginActivity : ComponentActivity() {
    var loginUser: LoginUser? = null

    private val signupResultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if ( result.resultCode == RESULT_OK ) {
            loginUser = (if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                result.data?.getParcelableExtra(SIGNUP_USER_INFO_KEY, LoginUser::class.java)
            } else {
                result.data?.getParcelableExtra<LoginUser>(SIGNUP_USER_INFO_KEY)
            })
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var idText by remember { mutableStateOf("") }
            var pwdText by remember { mutableStateOf("") }
            var isLoginEnable by remember { mutableStateOf(false) }

            val scope = rememberCoroutineScope()
            val snackbarHostState = remember { SnackbarHostState() }

            fun updateButtonState() {
                isLoginEnable = idText.isNotEmpty() && pwdText.isNotEmpty()
            }

            ATSOPTANDROIDTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    snackbarHost = {
                        SnackbarHost(hostState = snackbarHostState)
                    },
                ) { innerPadding ->
                    LoginScreen(
                        modifier = Modifier.padding(innerPadding),
                        idText = idText,
                        onIdChange = {
                            idText = it
                            updateButtonState()
                        },
                        pwdText = pwdText,
                        onPwdChange = {
                            pwdText = it
                            updateButtonState()
                        },
                        onLoginClick = {
                            when (isIdenticalLoginUserInfo(idText, pwdText)) {
                                true -> {
                                    startActivity(Intent(this, MainActivity::class.java).apply {
                                        flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                                        putExtra(USER_ID_KEY, loginUser!!.id)
                                    })
                                }
                                false -> scope.launch { snackbarHostState.showSnackbar("아이디 또는 비밀번호가 일치하지 않습니다.") }
                                null -> scope.launch { snackbarHostState.showSnackbar("회원 정보가 없습니다.") }
                            }
                        },
                        isLoginEnable = isLoginEnable,
                        onSignUpClick = {
                            val intent = Intent(this, SignupActivity::class.java)
                            signupResultLauncher.launch(intent)
                        }
                    )
                }
            }
        }
    }

    fun isIdenticalLoginUserInfo(id: String, pwd: String): Boolean? {
        if (loginUser == null) return null
        return loginUser?.id == id && pwd == loginUser?.password
    }

    companion object {
        const val SIGNUP_USER_INFO_KEY = "signup_user_info_key"
        const val USER_ID_KEY = "user_id_key"
    }
}