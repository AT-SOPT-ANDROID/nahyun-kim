package org.sopt.at.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.serialization.Serializable
import org.sopt.at.R
import org.sopt.at.presentation.home.component.BannerCarousel
import org.sopt.at.presentation.home.component.HomeTopBar
import org.sopt.at.presentation.home.component.RecommendContent
import org.sopt.at.ui.theme.ATSOPTANDROIDTheme
import org.sopt.at.ui.theme.White

@Serializable
data object Home

@Composable
fun HomeScreen(
    paddingValues: PaddingValues,
    viewModel: HomeViewModel = viewModel()
) {
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        HomeTopBar()
        HomeTabLayout() //TODO: 실제 TabLayout으로 변경
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(20.dp),
            contentPadding = PaddingValues(vertical = 12.dp),
            modifier = Modifier
                .fillMaxSize()
        ) {
            item {
                BannerCarousel()
            }
            item {
                RecommendContent(
                    modifier = Modifier,
                    textRes = R.string.home_recommend_top20,
                    isSupportRanking = true,
                    isShowMoreButton = false,
                    contentList = viewModel.top20Contents
                )
            }
            item {
                RecommendContent(
                    modifier = Modifier,
                    textRes = R.string.home_recommend_current_broadcast,
                    isSupportRanking = false,
                    isShowMoreButton = true,
                    contentList = viewModel.currentBroadCastContents
                )
            }
        }
    }
}

@Composable
fun HomeTabLayout(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        horizontalArrangement = Arrangement
            .spacedBy(
                space = 16.dp,
                alignment = Alignment.CenterHorizontally
            ),
    ) {
        val stringArr = stringArrayResource(R.array.home_tab_array)
        stringArr.forEach { text ->
            TabTextItem(Modifier, text)
        }
    }
}

@Composable
fun TabTextItem(
    modifier: Modifier = Modifier,
    text: String
) {
    Text(
        text = text,
        color = White,
        style = MaterialTheme.typography.bodyMedium,
        modifier = modifier
    )
}


@Preview(showBackground = true, backgroundColor = 0x0000000)
@Composable
private fun Preview() {
    ATSOPTANDROIDTheme {
        HomeScreen(PaddingValues())
    }
}