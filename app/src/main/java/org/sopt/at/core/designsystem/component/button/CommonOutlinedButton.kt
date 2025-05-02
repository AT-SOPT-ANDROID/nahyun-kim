package org.sopt.at.core.designsystem.component.button

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.at.R
import org.sopt.at.core.designsystem.theme.ATSOPTANDROIDTheme
import org.sopt.at.core.designsystem.theme.TvingTheme
import org.sopt.at.core.designsystem.theme.tvingSemiBold
import org.sopt.at.core.util.DisableRippleEffect

enum class ButtonSizeType() {
    LARGE, SMALL
}

@Composable
fun CommonOutlinedButton(
    modifier: Modifier = Modifier,
    sizeType: ButtonSizeType,
    @StringRes textResId: Int,
    onClick: () -> Unit
) {
    DisableRippleEffect  {
        OutlinedButton(
            modifier = when (sizeType) {
                ButtonSizeType.LARGE -> modifier.fillMaxWidth()
                ButtonSizeType.SMALL -> modifier
            },
            onClick = onClick,
            border = BorderStroke(
                width = dimensionResource(R.dimen.outline_button_stroke_width),
                color = TvingTheme.colors.gray3
            ),
            shape = RoundedCornerShape(dimensionResource(R.dimen.button_radius)),
            contentPadding = when (sizeType) {
                ButtonSizeType.LARGE -> PaddingValues(vertical = 14.dp)
                ButtonSizeType.SMALL -> PaddingValues(horizontal = 14.dp)
            }
        ) {
            Text(
                text = stringResource(textResId),
                fontSize = when (sizeType) {
                    ButtonSizeType.LARGE -> 14.sp
                    ButtonSizeType.SMALL -> 12.sp
                },
                fontFamily = tvingSemiBold,
                color = TvingTheme.colors.labelButtonText
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun FilledButtonPreview() {
    ATSOPTANDROIDTheme {
        CommonOutlinedButton(
            sizeType = ButtonSizeType.LARGE,
            textResId = R.string.next,
            onClick = {}
        )
    }
}