package org.sopt.at.ui.components.button

import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.at.R
import org.sopt.at.ui.theme.ATSOPTANDROIDTheme
import org.sopt.at.ui.theme.ButtonDisableText
import org.sopt.at.ui.theme.LabelButtonText

@Composable
fun LargeOutlinedButton(
    modifier: Modifier = Modifier,
    @StringRes buttonTextRes: Int = R.string.next,
    onClick: () -> Unit = {}
) {
    OutlinedButton(
        modifier = modifier
            .fillMaxWidth(),
        onClick = onClick,
        border = BorderStroke(dimensionResource(R.dimen.outline_button_stroke_width), ButtonDisableText),
        shape = RoundedCornerShape(dimensionResource(R.dimen.button_radius)),
        contentPadding = PaddingValues(vertical = 14.dp)
    ) {
        Text(
            text = stringResource(buttonTextRes),
            fontSize = 14.sp,
            fontWeight = FontWeight.ExtraBold,
            color = LabelButtonText
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    ATSOPTANDROIDTheme {
        LargeOutlinedButton()
    }
}