package org.sopt.at.presentation.auth.signup.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.at.R
import org.sopt.at.core.designsystem.component.button.ButtonSizeType
import org.sopt.at.core.designsystem.component.button.CommonOutlinedButton
import org.sopt.at.core.designsystem.component.textfield.CommonTextField
import org.sopt.at.core.designsystem.component.textfield.TextFieldType
import org.sopt.at.core.designsystem.theme.ATSOPTANDROIDTheme
import org.sopt.at.core.designsystem.theme.TvingTheme

@Composable
fun SignUpIdScreen(
    idText: String,
    onIdChange: (String) -> Unit,
    onNextClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .background(TvingTheme.colors.background)
            .padding(
                vertical = dimensionResource(R.dimen.screen_padding_vertical),
                horizontal = dimensionResource(R.dimen.screen_padding_horizontal)
            )
            .fillMaxSize()
            .imePadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = stringResource(R.string.signup_id_title),
            style = TvingTheme.typography.title,
            color = TvingTheme.colors.basicWhite,
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
            color = TvingTheme.colors.guideText,
            style = TvingTheme.typography.caption,
            modifier = Modifier.align(Alignment.Start)
        )
        Spacer(Modifier.weight(1f))
        CommonOutlinedButton(
            modifier = Modifier,
            sizeType = ButtonSizeType.LARGE,
            textResId = R.string.next,
            onClick = onNextClick
        )
    }
}

@Preview
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