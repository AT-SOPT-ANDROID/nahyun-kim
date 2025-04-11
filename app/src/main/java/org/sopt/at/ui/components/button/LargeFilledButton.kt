package org.sopt.at.ui.components.button

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
import org.sopt.at.ui.theme.ATSOPTANDROIDTheme
import org.sopt.at.ui.theme.ButtonDisableBg
import org.sopt.at.ui.theme.HintText
import org.sopt.at.ui.theme.Primary
import org.sopt.at.ui.theme.White

@Composable
fun LargeFilledButton(
    @StringRes buttonTextRes: Int,
    isButtonEnable: Boolean = false,
    onClick: () -> Unit = {}
) {
    Button( // 로그인 버튼
        modifier = Modifier
            .fillMaxWidth(),
        onClick = onClick,
        enabled = isButtonEnable,
        shape = RoundedCornerShape(dimensionResource(R.dimen.button_radius)),
        contentPadding = PaddingValues(vertical = 14.dp),
        colors = ButtonDefaults.buttonColors(
            disabledContainerColor = ButtonDisableBg,
            disabledContentColor = HintText,
            containerColor = Primary,
            contentColor = White
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
private fun Preview() {
    ATSOPTANDROIDTheme {
        LargeFilledButton(buttonTextRes = R.string.do_login)
    }
}