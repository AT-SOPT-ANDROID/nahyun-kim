package org.sopt.at.presentation.auth.signup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.at.R
import org.sopt.at.ui.common.button.ButtonSizeType
import org.sopt.at.ui.common.button.CommonOutlinedButton
import org.sopt.at.ui.common.textfield.CommonTextField
import org.sopt.at.ui.common.textfield.TextFieldType
import org.sopt.at.ui.theme.ATSOPTANDROIDTheme
import org.sopt.at.ui.theme.GuideText

@Composable
fun SignUpIdScreen(
    modifier: Modifier = Modifier,
    idText: String,
    onIdChange: (String) -> Unit,
    onNextClick: () -> Unit
) {
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
            Text(
                text = stringResource(R.string.signup_id_title),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
            )
            Spacer(Modifier.height(24.dp))
            CommonTextField(
                modifier = Modifier.fillMaxWidth(),
                height = 60.dp,
                type = TextFieldType.DEFAULT,
                value = idText,
                onValueChange = onIdChange,
                placeholder = stringResource(R.string.id_hint),
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
        CommonOutlinedButton(
            modifier = Modifier,
            sizeType = ButtonSizeType.LARGE,
            textResId = R.string.next,
            onClick = onNextClick
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SignupIdPreview() {
    ATSOPTANDROIDTheme {
        var text by remember { mutableStateOf("") }
        SignUpIdScreen(
            idText = text,
            onIdChange = { text = it },
            onNextClick = {}
        )
    }
}