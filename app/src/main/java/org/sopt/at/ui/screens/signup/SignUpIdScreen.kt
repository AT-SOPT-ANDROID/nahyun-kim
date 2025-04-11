package org.sopt.at.ui.screens.signup

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.at.R
import org.sopt.at.ui.components.button.LargeOutlinedButton
import org.sopt.at.ui.screens.signup.SignupActivity.Companion.isValidId
import org.sopt.at.ui.theme.ATSOPTANDROIDTheme
import org.sopt.at.ui.theme.GuideText

@Composable
fun SignUpIdScreen(
    modifier: Modifier = Modifier,
    idText: String = "",
    onIdChange: (String) -> Unit = {},
    onClickNextButton: () -> Unit = {}
) {
    val context = LocalContext.current

    Column(
        modifier = modifier
            .padding(
                vertical = dimensionResource(R.dimen.screen_padding_vertical),
                horizontal = dimensionResource(R.dimen.screen_padding_horizontal)
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
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
            )
            Spacer(Modifier.height(24.dp))
            OutlinedTextField(
                value = idText,
                onIdChange,
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
        LargeOutlinedButton(
            modifier = Modifier,
            onClick = {
                if (isValidId(idText)) {
                    onClickNextButton()
                } else {
                    Toast.makeText(context, context.getText(R.string.signup_id_error_form), Toast.LENGTH_SHORT).show()
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SignupIdPreview() {
    ATSOPTANDROIDTheme {
        SignUpIdScreen()
    }
}