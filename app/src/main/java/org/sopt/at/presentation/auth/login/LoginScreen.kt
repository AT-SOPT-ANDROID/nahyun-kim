package org.sopt.at.presentation.auth.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.launch
import org.sopt.at.R
import org.sopt.at.core.designsystem.component.appbar.CommonTopAppBar
import org.sopt.at.core.designsystem.component.button.CommonTextButton
import org.sopt.at.core.designsystem.component.button.LargeFilledButton
import org.sopt.at.core.designsystem.component.textfield.CommonTextField
import org.sopt.at.core.designsystem.component.textfield.TextFieldType
import org.sopt.at.core.designsystem.theme.ATSOPTANDROIDTheme
import org.sopt.at.core.designsystem.theme.TvingTheme
import org.sopt.at.core.state.UiState

@Composable
fun LoginRoute(
    paddingValues: PaddingValues,
    onBackClick: () -> Unit,
    onLoginClick: () -> Unit,
    onSignUpClick: () -> Unit,
    snackbarHostState: SnackbarHostState
) {
    LoginScreen(
        paddingValues = paddingValues,
        onBackClick = onBackClick,
        onLoginClick = onLoginClick,
        onSignUpClick = onSignUpClick,
        snackbarHostState = snackbarHostState
    )
}

@Composable
fun LoginScreen(
    paddingValues: PaddingValues,
    viewModel: LoginViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onLoginClick: () -> Unit,
    onSignUpClick: () -> Unit,
    snackbarHostState: SnackbarHostState
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val scope = rememberCoroutineScope()

    LaunchedEffect(uiState) {
        when (uiState) {
            is UiState.Success -> { onLoginClick() }
            is UiState.Error -> {
                scope.launch {
                    snackbarHostState.showSnackbar(
                        (uiState as UiState.Error).message
                    )
                }
            }
            else -> null
        }
    }

    Column(
        modifier = Modifier
            .padding(paddingValues)
            .padding(
                vertical = dimensionResource(R.dimen.screen_padding_vertical),
                horizontal = dimensionResource(R.dimen.screen_padding_horizontal)
            )
            .fillMaxSize(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        CommonTopAppBar(
            onBackClick = onBackClick
        ) { }
        Spacer(Modifier.height(50.dp))
        Text(
            text = stringResource(R.string.login_using_tving_id),
            style = TvingTheme.typography.title,
            modifier = Modifier
        )
        Spacer(Modifier.height(22.dp))
        Column (
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
        ) {
            CommonTextField(
                modifier = Modifier.fillMaxWidth(),
                type = TextFieldType.DEFAULT,
                value = state.userId,
                onValueChange = viewModel::updateId,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                placeholder = stringResource(R.string.id_hint),
            )
            Spacer(Modifier.height(10.dp))
            CommonTextField(
                modifier = Modifier.fillMaxWidth(),
                type = TextFieldType.PASSWORD,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                value = state.password,
                onValueChange = viewModel::updatePassword,
                placeholder = stringResource(R.string.password_hint),
            )
        }
        Spacer(Modifier.height(20.dp))
        LargeFilledButton( // 로그인 버튼
            modifier = Modifier.fillMaxWidth(),
            buttonTextRes = R.string.do_login,
            isButtonEnable = state.isButtonEnabled,
            onClick = viewModel::tryLogin
        )
        Spacer(Modifier.height(20.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
            ,
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CommonTextButton(buttonTextRes = R.string.login_find_id, onClick = {}) // 아이디 찾기
            ButtonDivider()
            CommonTextButton(buttonTextRes = R.string.login_find_pwd, onClick = {}) // 비밀번호 찾기
            ButtonDivider()
            CommonTextButton(buttonTextRes = R.string.sign_up, onClick = onSignUpClick) // 회원가입
        }
        Spacer(Modifier.height(20.dp))
        Text(
            text = stringResource(R.string.login_term_guide),
            color = TvingTheme.colors.guideText,
            textAlign = TextAlign.Center,
            style = TvingTheme.typography.caption,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun ButtonDivider() {
    Spacer(modifier = Modifier
        .width(dimensionResource(R.dimen.divider_width))
        .height(16.dp)
        .background(TvingTheme.colors.gray4)
    )
}

@Preview(showBackground = true, backgroundColor = 0x000000)
@Composable
private fun LoginPreview() {
    ATSOPTANDROIDTheme {
        LoginScreen(
            paddingValues = PaddingValues(),
            onBackClick = { },
            onLoginClick = { },
            onSignUpClick = { },
            snackbarHostState = SnackbarHostState()
        )
    }
}