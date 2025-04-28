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
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.unit.dp
import org.sopt.at.R
import org.sopt.at.ui.theme.Background
import org.sopt.at.ui.theme.ButtonDisableText
import org.sopt.at.ui.theme.White
import org.sopt.at.util.DisableRippleEffect

@Composable
fun HomeTabLayout(
    modifier: Modifier = Modifier,
    onTabClick: (Int) -> Unit,
    selectedTabIndex: Int?,
) {
    val stringArr = stringArrayResource(R.array.home_tab_array)

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
        itemsIndexed(stringArr) { index, text ->
            TabTextItem(
                index = index,
                text = text,
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