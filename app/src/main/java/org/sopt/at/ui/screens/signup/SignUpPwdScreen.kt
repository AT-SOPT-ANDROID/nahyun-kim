package org.sopt.at.ui.screens.signup

import android.widget.Toast
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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
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
import org.sopt.at.R
import org.sopt.at.ui.screens.signup.SignupActivity.Companion.isValidPassword
import org.sopt.at.ui.theme.ATSOPTANDROIDTheme
import org.sopt.at.ui.theme.ButtonDisableText
import org.sopt.at.ui.theme.GuideText

@Composable
fun SignUpPwdScreen(
    modifier: Modifier = Modifier,
    pwdText: String = "",
    onPwdChange: (String) -> Unit = {},
    onClickNextButton: () -> Unit = {}
) {
    val context = LocalContext.current

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
                value = pwdText,
                onPwdChange,
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
                if (isValidPassword(pwdText)) {
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
fun SignupPwdPreview() {
    ATSOPTANDROIDTheme {
        SignUpPwdScreen()
    }
}