package org.sopt.at.presentation.auth.signup

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import org.sopt.at.R
import org.sopt.at.presentation.auth.login.navigation.Login
import org.sopt.at.core.designsystem.component.appbar.CommonTopAppBar
import org.sopt.at.core.designsystem.theme.ATSOPTANDROIDTheme

@Composable
fun SignUpRoute(
    paddingValues: PaddingValues,
    onBackClick: () -> Unit,
    onLoginClick: (Login) -> Unit,
) {
    SignUpMainScreen(
        paddingValues = paddingValues,
        onBackClick = onBackClick,
        onLoginClick =  onLoginClick,
    )
}

@Composable
fun SignUpMainScreen(
    paddingValues: PaddingValues,
    onBackClick: () -> Unit,
    onLoginClick: (Login) -> Unit,
    viewModel: SignUpViewModel = viewModel(),
) {
    val currentStep by viewModel.currentStep.collectAsState()
    val registerInfo by viewModel.userInfo.collectAsState()

    val context = LocalContext.current

    // 뒤로가기 이벤트 정의
    BackHandler {
        val prevStep = SignUpStep.getPrevStep(viewModel.currentStep.value)

        when (prevStep) {
            null -> onBackClick()
            else -> {
                viewModel.updateStep(prevStep)
            }
        }
    }

    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
    ) {
        CommonTopAppBar(
            onBackClick = {
                when (currentStep) {
                    SignUpStep.ID -> onBackClick()
                    SignUpStep.PASSWORD -> viewModel.updateStep(SignUpStep.ID)
                }
            }
        ) {}
        //TODO: 회원가입 단계 Navigation으로 구분
        when (currentStep) {
            SignUpStep.ID -> {
                SignUpIdScreen(
                    idText = registerInfo.id,
                    onIdChange = viewModel::updateId,
                    onNextClick = {
                        if (viewModel.isValidId()) {
                            viewModel.updateStep(SignUpStep.PASSWORD)
                        } else {
                            Toast.makeText(
                                context,
                                context.getText(R.string.signup_id_error_form),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                )
            }

            SignUpStep.PASSWORD -> {
                SignUpPwdScreen(
                    pwdText = registerInfo.password,
                    onPwdChange = viewModel::updatePassword,
                    onNextClick = {
                        if (viewModel.isValidPassword()) {
                            Toast.makeText(context, "회원가입 완료!", Toast.LENGTH_SHORT).show()
                            onLoginClick(
                                registerInfo
                            )
                        } else {
                            Toast.makeText(
                                context,
                                context.getText(R.string.signup_pwd_error_form),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0x000000)
@Composable
private fun SignUpMainPreview() {
    ATSOPTANDROIDTheme {
        SignUpMainScreen(
            paddingValues = PaddingValues(),
            onBackClick = { },
            onLoginClick =  { }
        )
    }
}