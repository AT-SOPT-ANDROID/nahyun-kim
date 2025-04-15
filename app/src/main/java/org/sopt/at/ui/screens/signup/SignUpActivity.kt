package org.sopt.at.ui.screens.signup

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import org.sopt.at.R
import org.sopt.at.model.LoginUser
import org.sopt.at.ui.components.appbar.CommonTopAppBar
import org.sopt.at.ui.screens.login.LoginActivity
import org.sopt.at.ui.screens.login.LoginActivity.Companion.SIGNUP_USER_INFO_KEY
import org.sopt.at.ui.theme.ATSOPTANDROIDTheme

enum class SignUpStep {
    ID, PASSWORD
}

class SignupActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            var step by remember { mutableStateOf(SignUpStep.ID) }

            var idText by remember { mutableStateOf("") }
            var pwdText by remember { mutableStateOf("") }

            ATSOPTANDROIDTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        CommonTopAppBar(
                            onBackClick = {
                                when (step) {
                                    SignUpStep.ID -> finish()
                                    SignUpStep.PASSWORD -> step = SignUpStep.ID
                                }
                            }
                        ) { }
                    }
                ) { innerPadding ->
                    when (step) {
                        SignUpStep.ID -> {
                            SignUpIdScreen(
                                modifier = Modifier.padding(innerPadding),
                                idText = idText,
                                onIdChange = { idText = it },
                                onNextClick = {
                                    if (isValidId(idText)) {
                                        step = SignUpStep.PASSWORD
                                    } else {
                                        Toast.makeText(this, getText(R.string.signup_id_error_form), Toast.LENGTH_SHORT).show()
                                    }
                                }
                            )
                        }
                        SignUpStep.PASSWORD -> {
                            SignUpPwdScreen(
                                modifier = Modifier.padding(innerPadding),
                                pwdText = pwdText,
                                onPwdChange = { pwdText = it },
                                onNextClick = {
                                    if (isValidPassword(pwdText)) {
                                        Toast.makeText(this, "회원가입 완료!", Toast.LENGTH_SHORT).show()
                                        sendUserInfo(idText, pwdText)
                                        finish()
                                    } else {
                                        Toast.makeText(this, getText(R.string.signup_pwd_error_form), Toast.LENGTH_SHORT).show()
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
    }

    private fun sendUserInfo(id: String, pwd: String) {
        val intent = Intent(this, LoginActivity::class.java)
            .putExtra(SIGNUP_USER_INFO_KEY, LoginUser(id, pwd))
        setResult(RESULT_OK, intent)
    }

    companion object {
        // 정규식 패턴
        const val ID_PATTERN = "^(?=.*[a-z]+)(?=.*\\d*)[a-z\\d]{6,12}$"
        const val PWD_PATTERN = "^.*(?=^.{8,15}$)(?=.*\\d)(?=.*[a-zA-Z])(?=.*[~!@#$%^&*]).*$"

        fun isValidId(id: String): Boolean {
            return id.matches(ID_PATTERN.toRegex())
        }

        fun isValidPassword(pwd: String): Boolean {
            return pwd.matches(PWD_PATTERN.toRegex())
        }
    }
}