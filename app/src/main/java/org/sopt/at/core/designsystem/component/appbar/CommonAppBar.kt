package org.sopt.at.core.designsystem.component.appbar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import org.sopt.at.R
import org.sopt.at.core.designsystem.theme.TvingTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommonTopAppBar(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    trailingIcon: @Composable () -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(onClick = onBackClick) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_back),
                contentDescription = null,
                tint = TvingTheme.colors.basicWhite
            )
        }
        trailingIcon
    }
}