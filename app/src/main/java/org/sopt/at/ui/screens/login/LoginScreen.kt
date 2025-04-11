package org.sopt.at.ui.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.at.R
import org.sopt.at.ui.components.button.ArrowBackIcon
import org.sopt.at.ui.components.button.CommonTextButton
import org.sopt.at.ui.components.button.LargeFilledButton
import org.sopt.at.ui.components.textfield.CommonTextField
import org.sopt.at.ui.components.textfield.TextFieldType
import org.sopt.at.ui.theme.ATSOPTANDROIDTheme
import org.sopt.at.ui.theme.ButtonDisableBg

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    idText: String = "",
    onIdChange: (String) -> Unit = {},
    pwdText: String = "",
    onPwdChange: (String) -> Unit = {},
    onClickLoginButton: () -> Unit = {},
    onClickSignUpButton: () -> Unit = {}
) {
    var isButtonEnable by remember { mutableStateOf(false) }

    fun updateButtonState() {
        isButtonEnable = idText.isNotEmpty() && pwdText.isNotEmpty()
    }

    Column(
        modifier = modifier
            .padding(
                vertical = dimensionResource(R.dimen.screen_padding_vertical),
                horizontal = dimensionResource(R.dimen.screen_padding_horizontal)
            )
            .fillMaxSize(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        ArrowBackIcon()
        Spacer(Modifier.height(40.dp))
        Text(
            text = stringResource(R.string.login_using_tving_id),
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
        )
        Spacer(Modifier.height(18.dp))
        Column (
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
        ) {
            CommonTextField(
                modifier = Modifier.fillMaxWidth(),
                type = TextFieldType.DEFAULT,
                value = idText,
                onValueChange = {
                    onIdChange(it)
                    updateButtonState()
                },
                placeholder = stringResource(R.string.id_hint),
            )
            Spacer(Modifier.height(10.dp))
            CommonTextField(
                modifier = Modifier.fillMaxWidth(),
                type = TextFieldType.PASSWORD,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                value = pwdText,
                onValueChange = {
                    onPwdChange(it)
                    println(it)
                    updateButtonState()
                },
                placeholder = stringResource(R.string.password_hint),
            )
        }
        Spacer(Modifier.height(18.dp))
        LargeFilledButton( // 로그인 버튼
            buttonTextRes = R.string.do_login,
            isButtonEnable = isButtonEnable,
            onClick = onClickLoginButton
        )
        Spacer(Modifier.height(22.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
            ,
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CommonTextButton(R.string.login_find_id, onClick = {}) // 아이디 찾기
            ButtonDivider()
            CommonTextButton(R.string.login_find_pwd, onClick = {}) // 비밀번호 찾기
            ButtonDivider()
            CommonTextButton(R.string.sign_up, onClick = onClickSignUpButton) // 회원가입
        }
        Spacer(Modifier.height(30.dp))
        Text(
            stringResource(R.string.login_term_guide),
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun ButtonDivider() {
    Spacer(modifier = Modifier
        .width(dimensionResource(R.dimen.divider_width))
        .height(16.dp)
        .background(ButtonDisableBg)
    )
}

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    ATSOPTANDROIDTheme {
        LoginScreen()
    }
}