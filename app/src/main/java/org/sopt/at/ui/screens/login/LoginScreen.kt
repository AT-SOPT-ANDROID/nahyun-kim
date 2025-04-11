package org.sopt.at.ui.screens.login

import BackIcon
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.at.R
import org.sopt.at.ui.theme.ATSOPTANDROIDTheme
import org.sopt.at.ui.theme.ButtonDisableBg
import org.sopt.at.ui.theme.ButtonDisableText

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
    var isPwdVisible by remember { mutableStateOf(false) }

    val pwdIcon = if (isPwdVisible) painterResource(R.drawable.ic_password_show) else painterResource(
        R.drawable.ic_password_hide)

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
            OutlinedTextField(
                value = idText,
                onValueChange = {
                    onIdChange(it)
                    updateButtonState()
                },
                modifier = Modifier
                    .fillMaxWidth(),
                placeholder = { Text(stringResource(R.string.id_hint)) },
                singleLine = true
            )
            Spacer(Modifier.height(10.dp))
            OutlinedTextField(
                value = pwdText,
                onValueChange = {
                    onPwdChange(it)
                    updateButtonState()
                },
                modifier = Modifier
                    .fillMaxWidth(),
                placeholder = { Text(stringResource(R.string.password_hint)) },
                singleLine = true,
                visualTransformation = if (isPwdVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(
                        onClick = { isPwdVisible = !isPwdVisible },
                    ) {
                        Icon(
                            painter = pwdIcon,
                            contentDescription = "Visibility Icon",
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            )
        }
        Spacer(Modifier.height(18.dp))
        Button( // 로그인 버튼
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            onClick = {
                onClickLoginButton()
            },
            enabled = isButtonEnable,
            shape = RoundedCornerShape(dimensionResource(R.dimen.button_radius)),
            contentPadding = PaddingValues(vertical = 12.dp)
        ) {
            Text(
                text = stringResource(R.string.do_login),
                style = MaterialTheme.typography.labelLarge,
            )
        }
        Spacer(Modifier.height(22.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
            ,
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            LoginDefaultTextButton(R.string.login_find_id, clickEvent = {}) // 아이디 찾기
            ButtonDivider()
            LoginDefaultTextButton(R.string.login_find_pwd, clickEvent = {}) // 비밀번호 찾기
            ButtonDivider()
            LoginDefaultTextButton(R.string.sign_up, clickEvent = onClickSignUpButton)
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
fun ArrowBackIcon(modifier: Modifier = Modifier) {
    Image(
        imageVector = BackIcon,
        contentDescription = null,
        modifier = modifier
            .size(24.dp)
            .padding(1.dp),
        contentScale = ContentScale.FillBounds,
        alignment = Alignment.Center
    )
}

@Composable
fun LoginDefaultTextButton(@StringRes text: Int, clickEvent: () -> Unit) {
    TextButton(
        onClick = clickEvent
    ) {
        Text(
            stringResource(text),
            style = MaterialTheme.typography.labelMedium
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