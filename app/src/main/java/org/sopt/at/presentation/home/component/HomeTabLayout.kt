package org.sopt.at.presentation.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.sopt.at.core.designsystem.theme.Background
import org.sopt.at.core.designsystem.theme.ButtonDisableText
import org.sopt.at.core.designsystem.theme.White
import org.sopt.at.core.util.DisableRippleEffect

@Composable
fun HomeTabLayout(
    tabTitles: List<String>,
    onTabClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    selectedTabIndex: Int?,
) {
    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .background(Background)
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement
            .spacedBy(
                space = 12.dp,
                alignment = Alignment.CenterHorizontally
            ),
    ) {
        itemsIndexed(
            items = tabTitles,
            key = { index, title -> title },
        ) { index, title ->
            TabTextItem(
                index = index,
                text = title,
                onTabClick = { onTabClick(index) },
                selectedTabIndex = selectedTabIndex,
            )
        }
    }
}

@Composable
fun TabTextItem(
    modifier: Modifier = Modifier,
    index: Int,
    text: String,
    onTabClick: () -> Unit,
    selectedTabIndex: Int?,
) {
    val textColor = when (selectedTabIndex) {
        null, index -> White
        else -> ButtonDisableText
    }

    DisableRippleEffect {
        Text(
            text = text,
            color = textColor,
            style = MaterialTheme.typography.bodyMedium,
            modifier = modifier
                .clickable(
                    onClick = onTabClick
                )
                .padding(4.dp)
                .fillMaxWidth()
        )
    }
}