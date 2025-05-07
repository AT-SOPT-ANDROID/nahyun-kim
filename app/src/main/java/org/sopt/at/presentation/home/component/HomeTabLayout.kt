package org.sopt.at.presentation.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.ImmutableList
import org.sopt.at.core.designsystem.theme.TvingTheme
import org.sopt.at.core.util.DisableRippleEffect
import org.sopt.at.presentation.home.TabState

@Composable
fun HomeTabLayout(
    tabTitles: ImmutableList<String>,
    onTabClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    selectedTabState: TabState,
) {
    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .background(TvingTheme.colors.background)
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
                selectedTabState = selectedTabState,
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
    selectedTabState: TabState,
) {
    val colors = TvingTheme.colors
    val textColor = remember(selectedTabState) {
        when (selectedTabState) {
            is TabState.None -> colors.basicWhite
            is TabState.Selected -> if (selectedTabState.index == index) colors.basicWhite else colors.disableContent
        }
    }

    DisableRippleEffect {
        Text(
            text = text,
            color = textColor,
            style = TvingTheme.typography.labelButton,
            modifier = modifier
                .clickable(
                    onClick = onTabClick
                )
                .padding(4.dp)
                .fillMaxWidth()
        )
    }
}