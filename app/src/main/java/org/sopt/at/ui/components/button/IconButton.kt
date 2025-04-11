package org.sopt.at.ui.components.button

import BackIcon
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp

@Composable
fun ArrowBackIcon(modifier: Modifier = Modifier) {
    Image(
        imageVector = BackIcon,
        contentDescription = null,
        modifier = modifier
            .size(24.dp)
            .padding(1.dp),
        contentScale = ContentScale.FillBounds,
        alignment = Alignment.Center
    )
}