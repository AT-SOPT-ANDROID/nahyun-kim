package org.sopt.at.ui.components.button

import androidx.annotation.StringRes
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

@Composable
fun CommonTextButton(
    @StringRes buttonTextRes: Int,
    onClick: () -> Unit
) {
    TextButton(
        onClick = onClick
    ) {
        Text(
            stringResource(buttonTextRes),
            style = MaterialTheme.typography.labelMedium
        )
    }
}