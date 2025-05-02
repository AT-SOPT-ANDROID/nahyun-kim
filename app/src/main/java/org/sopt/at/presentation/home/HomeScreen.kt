package org.sopt.at.presentation.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch
import org.sopt.at.R
import org.sopt.at.presentation.model.TabGenreContent
import org.sopt.at.presentation.home.component.BannerCarousel
import org.sopt.at.presentation.home.component.HomeTabLayout
import org.sopt.at.presentation.home.component.HomeTopBar
import org.sopt.at.presentation.home.component.RecommendContent
import org.sopt.at.core.designsystem.theme.ATSOPTANDROIDTheme


@Composable
fun HomeRoute(
    paddingValues: PaddingValues,
    onProfileClick: () -> Unit
) {
    HomeScreen(
        paddingValues = paddingValues,
        onProfileClick = onProfileClick
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    paddingValues: PaddingValues,
    viewModel: HomeViewModel = viewModel(),
    onProfileClick: () -> Unit
) {
    val tabTitlesArray = stringArrayResource(R.array.home_tab_array)
    val tabTitles = remember(tabTitlesArray) { tabTitlesArray.toList() }

    var selectedTabIndex = viewModel.selectedTabIndex.collectAsState().value

    val scrollState = rememberLazyListState()
    val scope = rememberCoroutineScope()

    fun scrollToTop() {
        scope.launch {
            scrollState.animateScrollToItem(index = 0)
        }
    }

    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        LazyColumn(
            state = scrollState,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(vertical = 8.dp),
            modifier = Modifier
                .fillMaxSize()
        ) {
            item {
                HomeTopBar(
                    modifier = Modifier,
                    onLogoClick = {
                        viewModel.selectTab(null)
                        scrollToTop()
                    },
                    onProfileClick = onProfileClick
                )
            }
            stickyHeader {
                HomeTabLayout(
                    tabTitles = tabTitles,
                    selectedTabIndex = selectedTabIndex,
                    onTabClick = { tabIndex ->
                        viewModel.selectTab(tabIndex)
                        scrollToTop()
                    }
                )
            }
            item {
                BannerCarousel(
                    bannerImageUrls = TabGenreContent.getGenreById(selectedTabIndex)!!.bannerImages,
                )
            }
            item {
                RecommendContent(
                    modifier = Modifier,
                    title = if (selectedTabIndex == null) stringResource(R.string.home_recommend_top20) else stringResource(R.string.home_recommend_live_popular, tabTitles[selectedTabIndex]),
                    isSupportRanking = true,
                    imageUrls = TabGenreContent.getGenreById(selectedTabIndex)!!.posterImages,
                    isShowMoreButton = false
                )
            }
            item {
                RecommendContent(
                    modifier = Modifier,
                    title = stringResource(R.string.home_recommend_current_broadcast),
                    isSupportRanking = false,
                    isShowMoreButton = true,
                    imageUrls = TabGenreContent.getGenreById(selectedTabIndex)!!.posterImages
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0x00000)
@Composable
private fun Preview() {
    ATSOPTANDROIDTheme {
        HomeScreen(
            paddingValues = PaddingValues(),
            onProfileClick = { },
        )
    }
}