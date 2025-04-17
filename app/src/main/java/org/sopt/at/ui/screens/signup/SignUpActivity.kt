package org.sopt.at.ui.screens.signup

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import org.sopt.at.R
import org.sopt.at.ui.components.appbar.CommonTopAppBar
import org.sopt.at.ui.screens.login.LoginActivity
import org.sopt.at.ui.screens.login.LoginActivity.Companion.SIGNUP_USER_INFO_KEY
import org.sopt.at.ui.theme.ATSOPTANDROIDTheme

class SignUpActivity : ComponentActivity() {

    val viewModel: SignUpViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setBackPressedEvent()

        setContent {
            val currentStep by viewModel.currentStep.collectAsState()
            val registerInfo by viewModel.userInfo.collectAsState()

            ATSOPTANDROIDTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        CommonTopAppBar(
                            onBackClick = {
                                when (currentStep) {
                                    SignUpStep.ID -> finish()
                                    SignUpStep.PASSWORD -> viewModel.updateStep(SignUpStep.ID)
                                }
                            }
                        ) { }
                    }
                ) { innerPadding ->
                    //TODO: Jetpack Navigation 활용하는 방식 검토
                    when (currentStep) {
                        SignUpStep.ID -> {
                            SignUpIdScreen(
                                modifier = Modifier.padding(innerPadding),
                                idText = registerInfo.id,
                                onIdChange = viewModel::updateId,
                                onNextClick = {
                                    if (viewModel.isValidId()) {
                                        viewModel.updateStep(SignUpStep.PASSWORD)
                                    } else {
                                        Toast.makeText(this, getText(R.string.signup_id_error_form), Toast.LENGTH_SHORT).show()
                                    }
                                }
                            )
                        }
                        SignUpStep.PASSWORD -> {
                            SignUpPwdScreen(
                                modifier = Modifier.padding(innerPadding),
                                pwdText = registerInfo.password,
                                onPwdChange = viewModel::updatePassword,
                                onNextClick = {
                                    if (viewModel.isValidPassword()) {
                                        Toast.makeText(this, "회원가입 완료!", Toast.LENGTH_SHORT).show()
                                        sendUserInfo()
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

    private fun setBackPressedEvent() {
        // 안드로이드 기본 뒤로가기 이벤트 정의
        onBackPressedDispatcher.addCallback(this, object: OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val prevStep = SignUpStep.getPrevStep(viewModel.currentStep.value)

                when (prevStep) {
                    null -> finish()
                    else -> {
                        viewModel.updateStep(prevStep)
                    }
                }
            }
        })
    }

    private fun sendUserInfo() {
        val intent = Intent(this, LoginActivity::class.java)
            .putExtra(SIGNUP_USER_INFO_KEY, viewModel.userInfo.value)
        setResult(RESULT_OK, intent)
    }
}