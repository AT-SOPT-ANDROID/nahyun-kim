package org.sopt.at.ui.screens.signup

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.at.R
import org.sopt.at.ui.components.button.LargeOutlinedButton
import org.sopt.at.ui.components.textfield.CommonTextField
import org.sopt.at.ui.components.textfield.TextFieldType
import org.sopt.at.ui.screens.signup.SignupActivity.Companion.isValidPassword
import org.sopt.at.ui.theme.ATSOPTANDROIDTheme

@Composable
fun SignUpPwdScreen(
    modifier: Modifier = Modifier,
    pwdText: String = "",
    onPwdChange: (String) -> Unit = {},
    onClickNextButton: () -> Unit = {}
) {
    val context = LocalContext.current

    Column(
        modifier = modifier
            .padding(
                vertical = dimensionResource(R.dimen.screen_padding_vertical),
                horizontal = dimensionResource(R.dimen.screen_padding_horizontal)
            )
            .fillMaxSize()
            .imePadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(16.dp))
            Text(
                text = stringResource(R.string.signup_pwd_title),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
            )
            Spacer(Modifier.height(24.dp))
            CommonTextField(
                modifier = Modifier.fillMaxWidth(),
                type = TextFieldType.PASSWORD,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                value = pwdText,
                onValueChange = onPwdChange,
                placeholder = stringResource(R.string.password_hint),
            )
            Spacer(Modifier.height(12.dp))
            Text(
                text = stringResource(R.string.signup_pwd_guide),
                style = MaterialTheme.typography.labelSmall,
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth()
            )
        }
        LargeOutlinedButton(
            modifier = Modifier,
            onClick = {
                if (isValidPassword(pwdText)) {
                    onClickNextButton()
                } else {
                    Toast.makeText(context, context.getText(R.string.signup_pwd_error_form), Toast.LENGTH_SHORT).show()
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SignupPwdPreview() {
    ATSOPTANDROIDTheme {
        SignUpPwdScreen()
    }
}