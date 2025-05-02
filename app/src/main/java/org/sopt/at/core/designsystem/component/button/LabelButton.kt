package org.sopt.at.core.designsystem.component.button

import androidx.annotation.StringRes
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import org.sopt.at.core.designsystem.theme.TvingTheme

@Composable
fun CommonTextButton(
    modifier: Modifier = Modifier,
    @StringRes buttonTextRes: Int,
    onClick: () -> Unit
) {
    TextButton(
        modifier = modifier,
        onClick = onClick
    ) {
        Text(
            text = stringResource(buttonTextRes),
            color = TvingTheme.colors.labelButtonText,
            style = MaterialTheme.typography.labelMedium
        )
    }
}