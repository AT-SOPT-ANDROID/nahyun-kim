package org.sopt.at.presentation.auth.signup

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import org.sopt.at.core.designsystem.component.appbar.CommonTopAppBar
import org.sopt.at.core.designsystem.theme.ATSOPTANDROIDTheme
import org.sopt.at.core.state.UiState
import org.sopt.at.presentation.auth.login.navigation.Login

@Composable
fun SignUpRoute(
    paddingValues: PaddingValues,
    onBackClick: () -> Unit,
    onNextClick: (Login) -> Unit,
) {
    SignUpMainScreen(
        paddingValues = paddingValues,
        onBackClick = onBackClick,
        onNextClick =  onNextClick,
    )
}

@Composable
fun SignUpMainScreen(
    paddingValues: PaddingValues,
    onBackClick: () -> Unit,
    onNextClick: (Login) -> Unit,
    viewModel: SignUpViewModel = viewModel(),
) {
    val state by viewModel.state.collectAsState()
    val uiState by viewModel.uiState.collectAsState()

    val context = LocalContext.current

    LaunchedEffect(uiState) {
        when (uiState) {
            is UiState.Success -> {
                Toast.makeText(context, "회원가입 완료!", Toast.LENGTH_SHORT).show()
                onNextClick(Login(
                    state.userId,
                    state.password
                ))
            }
            is UiState.Error -> {
                Toast.makeText(context, (uiState as UiState.Error).message, Toast.LENGTH_SHORT).show()
            }
            else -> null
        }
    }

    // 뒤로가기 이벤트 정의
    BackHandler {
        when (state.currentStep) {
            SignUpStep.ID -> onBackClick()
            else -> viewModel.updateStep(MoveDirection.PREV)
        }
    }

    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
    ) {
        CommonTopAppBar(
            onBackClick = {
                when (state.currentStep) {
                    SignUpStep.ID -> onBackClick()
                    else -> viewModel.updateStep(MoveDirection.PREV)
                }
            }
        ) {}
        //TODO: 회원가입 단계 Navigation으로 구분
        when (state.currentStep) {
            SignUpStep.ID -> {
                SignUpIdScreen(
                    idText = state.userId,
                    onIdChange = viewModel::updateId,
                    onNextClick = viewModel::onNextClick
                )
            }

            SignUpStep.PASSWORD -> {
                SignUpPwdScreen(
                    pwdText = state.password,
                    onPwdChange = viewModel::updatePassword,
                    onNextClick = viewModel::onNextClick
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
            onNextClick =  { }
        )
    }
}