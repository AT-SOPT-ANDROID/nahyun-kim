package org.sopt.at.core.designsystem.component.button

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.at.R
import org.sopt.at.core.designsystem.theme.ATSOPTANDROIDTheme
import org.sopt.at.core.designsystem.theme.TvingTheme


@Composable
fun LargeFilledButton(
    modifier: Modifier = Modifier,
    @StringRes buttonTextRes: Int,
    isButtonEnable: Boolean = false,
    onClick: () -> Unit
) {
    Button( // 로그인 버튼
        modifier = modifier.fillMaxWidth(),
        onClick = onClick,
        enabled = isButtonEnable,
        shape = RoundedCornerShape(dimensionResource(R.dimen.button_radius)),
        contentPadding = PaddingValues(vertical = 14.dp),
        colors = ButtonDefaults.buttonColors(
            disabledContainerColor = TvingTheme.colors.buttonDisableBg,
            disabledContentColor = TvingTheme.colors.buttonDisableText,
            containerColor = TvingTheme.colors.primary,
            contentColor = TvingTheme.colors.onPrimary
        )
    ) {
        Text(
            text = stringResource(buttonTextRes),
            style = MaterialTheme.typography.labelLarge,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun FilledButtonPreview() {
    ATSOPTANDROIDTheme {
        LargeFilledButton(
            buttonTextRes = R.string.do_login,
            onClick = {}
        )
    }
}