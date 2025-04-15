package org.sopt.at.ui.components.button

import androidx.annotation.StringRes
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource

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
            style = MaterialTheme.typography.labelMedium
        )
    }
}