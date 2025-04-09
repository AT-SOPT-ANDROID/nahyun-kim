package org.sopt.at

import BackIcon
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import org.sopt.at.ui.theme.ATSOPTANDROIDTheme
import org.sopt.at.ui.theme.ButtonDisableText
import org.sopt.at.ui.theme.TextFieldBg

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ATSOPTANDROIDTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LoginScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun LoginScreen(modifier: Modifier = Modifier) {
    var idText by remember { mutableStateOf("") }
    var pwdText by remember { mutableStateOf("") }
    var isButtonEnable by remember { mutableStateOf(false) }
    var isPwdVisible by remember { mutableStateOf(false) }

    val pwdIcon = if (isPwdVisible) painterResource(R.drawable.ic_password_show) else painterResource(R.drawable.ic_password_hide)

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
                    idText = it
                    isButtonEnable = idText.isNotEmpty()
                },
                modifier = Modifier
                    .fillMaxWidth(),
                placeholder = { Text(stringResource(R.string.id_hint)) },
                singleLine = true
            )
            Spacer(Modifier.height(10.dp))
            OutlinedTextField(
                value = pwdText,
                onValueChange = { pwdText = it },
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
                colors = OutlinedTextFieldDefaults.colors(
                    TextFieldBg
                )
            )
        }
        Spacer(Modifier.height(18.dp))
        Button( // 로그인 버튼
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            onClick = { },
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
            ButtonText(R.string.login_find_id)
            ButtonDivider()
            ButtonText(R.string.login_find_pwd)
            ButtonDivider()
            ButtonText(R.string.sign_up)
        }
        Spacer(Modifier.height(30.dp))
        Text(
            stringResource(R.string.login_term_guide),
            color = Color(0xFF7E7E7E),
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
        modifier = modifier.size(24.dp).padding(1.dp),
        contentScale = ContentScale.FillBounds,
        alignment = Alignment.Center
    )
}

@Composable
fun ButtonText(stringResId: Int) {
    Text(
        stringResource(stringResId),
        color = Color(0xFFB5B5B5),
        fontSize = 14.sp,
    )
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