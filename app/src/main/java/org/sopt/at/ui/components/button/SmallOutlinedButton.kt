package org.sopt.at.ui.components.button

import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.at.R
import org.sopt.at.ui.theme.ATSOPTANDROIDTheme
import org.sopt.at.ui.theme.ButtonDisableText
import org.sopt.at.ui.theme.LabelButtonText

@Composable
fun SmallOutlinedButton (
    modifier: Modifier = Modifier,
    @StringRes buttonTextRes: Int,
    onClick: () -> Unit = {}
) {
    OutlinedButton(
        modifier = modifier,
        onClick = onClick,
        contentPadding = PaddingValues(horizontal = 14.dp),
        shape = RoundedCornerShape(dimensionResource(R.dimen.button_radius)),
        border = BorderStroke(dimensionResource(R.dimen.outline_button_stroke_width), ButtonDisableText)
    ) {
        Text(
            text = stringResource(buttonTextRes),
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.ExtraBold,
            color = LabelButtonText
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    ATSOPTANDROIDTheme {
        SmallOutlinedButton(buttonTextRes = R.string.my_profile_switch)
    }
}