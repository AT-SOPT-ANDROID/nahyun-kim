@file:Suppress("UNCHECKED_CAST")

package org.sopt.at

import BackIcon
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import org.sopt.at.model.LoginUser
import org.sopt.at.ui.theme.ATSOPTANDROIDTheme
import org.sopt.at.ui.theme.ButtonDisableText
import org.sopt.at.ui.theme.GuideText


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
                        idText = idText,
                        onIdChange = { idText = it },
                        pwdText = pwdText,
                        onPwdChange = { pwdText = it },
                        onClickLoginButton = {
                            if (isIdenticalLoginUserInfo(idText, pwdText)) {
                                startActivity(Intent(this, MyActivity::class.java))
                            } else {
                                val message = if (loginUser == null) {
                                    "회원 정보가 없습니다."
                                } else {
                                    "아이디 또는 비밀번호가 일치하지 않습니다."
                                }
                                scope.launch {
                                    snackbarHostState.showSnackbar(message)
                                }
                            }
                        },
                        onClickSignUpButton = {
                            val intent = Intent(this, SignupActivity::class.java)
                            signupResultLauncher.launch(intent)
                        }
                    )
                }
            }
        }
    }

    fun isIdenticalLoginUserInfo(id: String, pwd: String): Boolean {
        return loginUser?.id == id && pwd == loginUser?.password
    }

    companion object {
        const val SIGNUP_USER_INFO_KEY = "signup_user_info_key"
    }
}

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

    val pwdIcon = if (isPwdVisible) painterResource(R.drawable.ic_password_show) else painterResource(R.drawable.ic_password_hide)

    fun updateButtonState() {
        isButtonEnable = idText.isNotEmpty() && pwdText.isNotEmpty()
    }

    Column(
        modifier = modifier
            .padding(
                vertical = 12.dp,
                horizontal = 16.dp
            )
            .fillMaxSize(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        ArrowBackIcon()
        Spacer(Modifier.height(40.dp))
        Text(
            text = stringResource(R.string.login_using_tving_id),
            color = Color.White,
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
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
            shape = RoundedCornerShape(8.dp),
            contentPadding = PaddingValues(vertical = 12.dp)

        ) {
            Text(
                text = stringResource(R.string.do_login),
                fontSize = 16.sp,
                fontWeight = FontWeight.ExtraBold
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
            color = GuideText,
            fontSize = 12.sp,
            textAlign = TextAlign.Center,
            lineHeight = 1.3.em,
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
            color = Color(0xFFB5B5B5),
            fontSize = 14.sp,
        )
    }
}

@Composable
fun ButtonDivider() {
    Spacer(modifier = Modifier
        .width(0.8.dp)
        .height(16.dp)
        .background(ButtonDisableText)
    )
}

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    ATSOPTANDROIDTheme {
        LoginScreen()
    }
}