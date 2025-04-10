package org.sopt.at

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.at.SignupActivity.Companion.idRange
import org.sopt.at.SignupActivity.Companion.idRegex
import org.sopt.at.SignupActivity.Companion.passwordRegex
import org.sopt.at.ui.theme.ATSOPTANDROIDTheme
import org.sopt.at.ui.theme.ButtonDisableText
import org.sopt.at.ui.theme.GuideText
import java.util.regex.Pattern

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

            ATSOPTANDROIDTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { },
                            navigationIcon = {
                                IconButton(onClick = {
                                    when (step) {
                                        SignUpStep.ID -> finish()
                                        SignUpStep.PASSWORD -> step = SignUpStep.ID
                                    }
                                }) {
                                    ArrowBackIcon()
                                }
                            },
                            colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
                        )
                    },
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    when (step) {
                        SignUpStep.ID -> {
                            SignUpIdScreen(
                                modifier = Modifier.padding(innerPadding),
                                onClickNextButton = { step = SignUpStep.PASSWORD }
                            )
                        }
                        SignUpStep.PASSWORD -> {
                            SignUpPwdScreen(
                                modifier = Modifier.padding(innerPadding),
                                onClickNextButton = {
                                    //TODO: 로그인 화면으로 이동
                                    Toast.makeText(this, "회원가입 완료!", Toast.LENGTH_SHORT).show()
                                }
                            )
                        }
                    }
                }
            }
        }
    }

    companion object {
        const val idRegex = "^[a-zA-Z0-9]*$" // 아이디 유효성
        val idRange = 6..12

        const val passwordRegex = "^.*(?=^.{8,15}$)(?=.*\\d)(?=.*[a-zA-Z])(?=.*[~!@#$%^&*]).*$" // 회원가입 유효성
    }
}

fun isValidId(id: String): Boolean {
    return Pattern.matches(idRegex, id) && id.length in idRange
}

fun isValidPassword(pwd: String): Boolean {
    return Pattern.matches(passwordRegex, pwd)
}

@Composable
fun SignUpIdScreen(
    modifier: Modifier = Modifier,
    onClickNextButton: () -> Unit
    ) {
    val context = LocalContext.current

    var idText by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .padding(
                vertical = 12.dp,
                horizontal = 16.dp
            )
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(16.dp))
            Text(
                text = stringResource(R.string.signup_id_title),
                color = Color.White,
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
            )
            Spacer(Modifier.height(24.dp))
            OutlinedTextField(
                value = idText,
                onValueChange = {
                    idText = it
                },
                modifier = Modifier
                    .fillMaxWidth(),
                placeholder = { Text(stringResource(R.string.id_hint)) },
                singleLine = true
            )
            Spacer(Modifier.height(12.dp))
            Text(
                text = stringResource(R.string.signup_id_guide),
                color = GuideText,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth()
            )
        }
        OutlinedButton( // 다음 버튼
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp)
                .border(
                    width = 0.5.dp,
                    color = ButtonDisableText,
                    shape = RoundedCornerShape(4.dp)
                ),
            onClick = {
                if (isValidId(idText)) {
                    onClickNextButton()
                } else {
                    Toast.makeText(context, context.getText(R.string.signup_id_error_form), Toast.LENGTH_SHORT).show()
                }
            },
            shape = RoundedCornerShape(8.dp),
            contentPadding = PaddingValues(vertical = 12.dp)

        ) {
            Text(
                text = stringResource(R.string.next),
                fontSize = 16.sp,
                fontWeight = FontWeight.ExtraBold,
                color = ButtonDisableText
            )
        }
    }
}

@Composable
fun SignUpPwdScreen(
    modifier: Modifier = Modifier,
    onClickNextButton: () -> Unit
) {
    val context = LocalContext.current

    var passwordText by remember { mutableStateOf("") }
    var isPwdVisible by remember { mutableStateOf(false) }

    val pwdIcon = if (isPwdVisible) painterResource(R.drawable.ic_password_show) else painterResource(R.drawable.ic_password_hide)

    Column(
        modifier = modifier
            .padding(
                vertical = 12.dp,
                horizontal = 16.dp
            )
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(16.dp))
            Text(
                text = stringResource(R.string.signup_pwd_title),
                color = Color.White,
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
            )
            Spacer(Modifier.height(24.dp))
            OutlinedTextField(
                value = passwordText,
                onValueChange = {
                    passwordText = it
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
                },
            )
            Spacer(Modifier.height(12.dp))
            Text(
                text = stringResource(R.string.signup_pwd_guide),
                color = GuideText,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth()
            )
        }
        OutlinedButton( // 다음 버튼
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp)
                .border(
                    width = 0.5.dp,
                    color = ButtonDisableText,
                    shape = RoundedCornerShape(4.dp)
                ),
            onClick = {
                if (isValidPassword(passwordText)) {
                    onClickNextButton()
                } else {
                    Toast.makeText(context, context.getText(R.string.signup_pwd_error_form), Toast.LENGTH_SHORT).show()
                }
            },
            shape = RoundedCornerShape(8.dp),
            contentPadding = PaddingValues(vertical = 12.dp)

        ) {
            Text(
                text = stringResource(R.string.next),
                fontSize = 16.sp,
                fontWeight = FontWeight.ExtraBold,
                color = ButtonDisableText
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignupIdPreview() {
    ATSOPTANDROIDTheme {
        SignUpIdScreen(onClickNextButton = {})
    }
}

@Preview(showBackground = true)
@Composable
fun SignupPwdPreview() {
    ATSOPTANDROIDTheme {
        SignUpPwdScreen(onClickNextButton = {})
    }
}