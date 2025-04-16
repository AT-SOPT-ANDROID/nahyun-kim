package org.sopt.at.ui.screens.main

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
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.serialization.Serializable
import org.sopt.at.ui.screens.main.navigation.BottomNavItem
import org.sopt.at.ui.theme.ATSOPTANDROIDTheme

@Serializable
data object Home

@Composable
fun HomeScreen(
    paddingValues: PaddingValues,
) {
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = BottomNavItem.HOME.label,
            style = MaterialTheme.typography.titleMedium
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0x0000000)
@Composable
private fun Preview() {
    ATSOPTANDROIDTheme {
        HomeScreen(PaddingValues())
    }
}