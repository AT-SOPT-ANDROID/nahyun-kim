package org.sopt.at.presentation.history

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import org.sopt.at.core.designsystem.theme.TvingTheme
import org.sopt.at.presentation.main.MainNavTab

@Composable
fun HistoryRoute(
    paddingValues: PaddingValues
) {
    HistoryScreen(paddingValues)
}

@Composable
fun HistoryScreen(
    paddingValues: PaddingValues
) {
    Column(
        modifier = Modifier
            .background(TvingTheme.colors.background)
            .padding(paddingValues)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(MainNavTab.HISTORY.labelRes),
            style = TvingTheme.typography.subTitle
        )
    }
}