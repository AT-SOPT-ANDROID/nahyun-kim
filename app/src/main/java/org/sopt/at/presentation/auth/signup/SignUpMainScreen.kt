package org.sopt.at.presentation.auth.signup

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.sopt.at.core.designsystem.component.appbar.CommonTopAppBar
import org.sopt.at.core.designsystem.theme.ATSOPTANDROIDTheme
import org.sopt.at.core.designsystem.theme.TvingTheme
import org.sopt.at.core.state.UiState
import org.sopt.at.presentation.auth.signin.navigation.SignIn

@Composable
fun SignUpRoute(
    paddingValues: PaddingValues,
    navigateBack: () -> Unit,
    navigateToLogin: (SignIn) -> Unit,
    viewModel: SignUpViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val context = LocalContext.current

    val onBackClick: () -> Unit = when (state.currentStep) {
        SignUpStep.ID -> navigateBack
        else -> { { viewModel.updateStep(MoveDirection.PREV) } }
    }

    LaunchedEffect(uiState) {
        when (uiState) {
            is UiState.Success -> {
                Toast.makeText(context, "회원가입 완료!", Toast.LENGTH_SHORT).show()
                navigateToLogin(SignIn(
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

    BackHandler {
        onBackClick()
    }

    SignUpMainScreen(
        paddingValues = paddingValues,
        onBackClick = onBackClick,
        onNextClick =  viewModel::onNextClick,
        currentStep = state.currentStep,
        userId = state.userId,
        onUserIdChanged = viewModel::updateId,
        password = state.password,
        onPasswordChanged = viewModel::updatePassword
    )
}

@Composable
fun SignUpMainScreen(
    paddingValues: PaddingValues,
    onBackClick: () -> Unit,
    onNextClick: () -> Unit,
    currentStep: SignUpStep,
    userId: String,
    onUserIdChanged: (String) -> Unit,
    password: String,
    onPasswordChanged: (String) -> Unit,
) {
    Column(
        modifier = Modifier
            .background(TvingTheme.colors.background)
            .padding(paddingValues)
            .fillMaxSize()
    ) {
        CommonTopAppBar(
            onBackClick = onBackClick
        ) {}
        //TODO: 회원가입 단계 Navigation으로 구분 (중첩 네비게이션 활용)
        when (currentStep) {
            SignUpStep.ID -> {
                SignUpIdScreen(
                    idText = userId,
                    onIdChange = onUserIdChanged,
                    onNextClick = onNextClick
                )
            }

            SignUpStep.PASSWORD -> {
                SignUpPwdScreen(
                    pwdText = password,
                    onPwdChange = onPasswordChanged,
                    onNextClick = onNextClick
                )
            }
        }
    }
}

@Preview
@Composable
private fun SignUpMainPreview() {
    ATSOPTANDROIDTheme {
        SignUpMainScreen(
            paddingValues = PaddingValues(),
            onBackClick = { },
            onNextClick = { },
            currentStep = SignUpStep.ID,
            userId = "",
            onUserIdChanged = { },
            password = "",
            onPasswordChanged = { },
        )
    }
}