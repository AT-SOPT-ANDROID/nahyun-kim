package org.sopt.at.presentation.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.serialization.Serializable
import org.sopt.at.R
import org.sopt.at.presentation.home.component.BannerCarousel
import org.sopt.at.presentation.home.component.HomeTopBar
import org.sopt.at.presentation.home.component.RecommendContent
import org.sopt.at.presentation.home.model.TabGenreResource
import org.sopt.at.ui.theme.ATSOPTANDROIDTheme
import org.sopt.at.ui.theme.Background
import org.sopt.at.ui.theme.ButtonDisableText
import org.sopt.at.ui.theme.White

@Serializable
data object Home

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    paddingValues: PaddingValues,
    viewModel: HomeViewModel = viewModel(),
    userId: String,
    navigationToMy: (id: String) -> Unit
) {
    val tabTitles = stringArrayResource(R.array.home_tab_array)
    var selectedTabIndex = viewModel.selectedTabIndex.collectAsState()
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        HomeTopBar(
            modifier = Modifier,
            onProfileClick = { navigationToMy(userId) }
        )
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(20.dp),
            contentPadding = PaddingValues(vertical = 8.dp),
            modifier = Modifier
                .fillMaxSize()
        ) {
            stickyHeader {
                HomeScrollableTabRow(
                    tabTitles = tabTitles.toList(),
                    selectedTabIndex = selectedTabIndex.value,
                ) { tabIndex ->
                    viewModel.selectTab(tabIndex)
                }
            }
            item {
                BannerCarousel(
                    bannerImageUrls = TabGenreResource.getDataById(selectedTabIndex.value)!!.bannerImages,
                )
            }
            item {
                RecommendContent(
                    modifier = Modifier,
                    title = stringResource(R.string.home_recommend_live_popular, tabTitles[selectedTabIndex.value]),
                    isSupportRanking = true,
                    imageUrls = TabGenreResource.getDataById(selectedTabIndex.value)!!.posterImages,
                    isShowMoreButton = false
                )
            }
            item {
                RecommendContent(
                    modifier = Modifier,
                    title = stringResource(R.string.home_recommend_current_broadcast),
                    isSupportRanking = false,
                    isShowMoreButton = true,
                    imageUrls = TabGenreResource.getDataById(selectedTabIndex.value)!!.posterImages.reversed()
                )
            }
        }
    }
}

@Composable
fun HomeScrollableTabRow(
    modifier: Modifier = Modifier,
    tabTitles: List<String>,
    selectedTabIndex: Int,
    onTabClick: (Int) -> Unit
) {
    ScrollableTabRow(
        modifier = modifier.fillMaxWidth(),
        selectedTabIndex = selectedTabIndex,
        containerColor = Background,
        edgePadding = 0.dp,
        indicator = {}, // 인디케이터 제거
        divider = {}, // 경계선 제거
    ) {
        tabTitles.forEachIndexed { tabIndex, tab ->
            Tab(
                selected = selectedTabIndex == tabIndex,
                onClick = { onTabClick(tabIndex) },
                selectedContentColor = White,
                unselectedContentColor = ButtonDisableText,
                text = {
                    Text(
                        text = tab,
                        style = MaterialTheme.typography.bodyMedium,
                        color = LocalContentColor.current,
                    )
                },
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0x00000)
@Composable
private fun Preview() {
    ATSOPTANDROIDTheme {
        HomeScreen(
            paddingValues = PaddingValues(),
            userId = "",
            navigationToMy = { },
        )
    }
}