package org.sopt.at.presentation.live

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import kotlinx.serialization.Serializable
import org.sopt.at.presentation.main.navigation.BottomNavItem

@Serializable
data object Live

@Composable
fun LiveScreen(paddingValues: PaddingValues,
) {
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(BottomNavItem.LIVE.labelRes),
            style = MaterialTheme.typography.titleMedium
        )
    }
}