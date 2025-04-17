package org.sopt.at.ui.screens.login

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch
import org.sopt.at.model.LoginUser
import org.sopt.at.ui.screens.main.MainActivity
import org.sopt.at.ui.screens.signup.SignUpActivity
import org.sopt.at.ui.theme.ATSOPTANDROIDTheme
import kotlin.getValue


class LoginActivity : ComponentActivity() {
    val viewModel: LoginViewModel by viewModels()

    private val signupResultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if ( result.resultCode == RESULT_OK ) {
            viewModel.setRegisterUserInfo(
                (if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    result.data?.getParcelableExtra(SIGNUP_USER_INFO_KEY, LoginUser::class.java)
                } else {
                    result.data?.getParcelableExtra<LoginUser>(SIGNUP_USER_INFO_KEY)
                })
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val loginInfo by viewModel.loginUserInfo.collectAsState()
            val isLoginEnable by viewModel.isButtonEnable.collectAsState()

            val scope = rememberCoroutineScope()
            val snackbarHostState = remember { SnackbarHostState() }

            ATSOPTANDROIDTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    snackbarHost = {
                        SnackbarHost(hostState = snackbarHostState)
                    },
                ) { innerPadding ->
                    LoginScreen(
                        modifier = Modifier.padding(innerPadding),
                        idText = loginInfo.id,
                        onIdChange = viewModel::updateId,
                        pwdText = loginInfo.password,
                        onPwdChange = viewModel::updatePassword,
                        onLoginClick = {
                            when (viewModel.isIdenticalUser()) {
                                true -> {
                                    startActivity(Intent(this, MainActivity::class.java).apply {
                                        flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                                        putExtra(USER_ID_KEY, viewModel.registerUserInfo.value!!.id)
                                    })
                                }
                                false -> scope.launch { snackbarHostState.showSnackbar("아이디 또는 비밀번호가 일치하지 않습니다.") }
                                null -> scope.launch { snackbarHostState.showSnackbar("회원 정보가 없습니다.") }
                            }
                        },
                        isLoginEnable = isLoginEnable,
                        onSignUpClick = {
                            val intent = Intent(this, SignUpActivity::class.java)
                            signupResultLauncher.launch(intent)
                        }
                    )
                }
            }
        }
    }

    companion object {
        const val SIGNUP_USER_INFO_KEY = "signup_user_info_key"
        const val USER_ID_KEY = "user_id_key"
    }
}