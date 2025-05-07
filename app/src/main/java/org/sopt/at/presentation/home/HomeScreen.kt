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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.collections.immutable.toImmutableList
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
    navigateToMy: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val selectedTabIndex by viewModel.selectedTabState.collectAsStateWithLifecycle()

    HomeScreen(
        paddingValues = paddingValues,
        onProfileClick = navigateToMy,
        selectedTabState = selectedTabIndex,
        selectTab = viewModel::selectTab
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    paddingValues: PaddingValues,
    onProfileClick: () -> Unit,
    selectedTabState: TabState,
    selectTab: (TabState) -> Unit
) {
    val tabTitles = stringArrayResource(R.array.home_tab_array).toImmutableList()

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
                        selectTab(TabState.None)
                        scrollToTop()
                    },
                    onProfileClick = onProfileClick
                )
            }
            stickyHeader {
                HomeTabLayout(
                    tabTitles = tabTitles,
                    selectedTabState = selectedTabState,
                    onTabClick = { tabIndex ->
                        selectTab(TabState.Selected(tabIndex))
                        scrollToTop()
                    }
                )
            }
            item {
                BannerCarousel(
                    bannerImageUrls = TabGenreContent.getGenreByState(selectedTabState).bannerImages,
                )
            }
            item {
                RecommendContent(
                    modifier = Modifier,
                    title = when (selectedTabState) {
                        is TabState.None -> stringResource(R.string.home_recommend_top20)
                        is TabState.Selected -> stringResource(
                            R.string.home_recommend_live_popular,
                            tabTitles[selectedTabState.index]
                        )
                    },
                    isSupportRanking = true,
                    imageUrls = TabGenreContent.getGenreByState(selectedTabState).posterImages,
                    isShowMoreButton = false
                )
            }
            item {
                RecommendContent(
                    modifier = Modifier,
                    title = stringResource(R.string.home_recommend_current_broadcast),
                    isSupportRanking = false,
                    isShowMoreButton = true,
                    imageUrls = TabGenreContent.getGenreByState(selectedTabState).posterImages
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0x00000)
@Composable
private fun HomePreview() {
    ATSOPTANDROIDTheme {
        HomeScreen(
            paddingValues = PaddingValues(),
            onProfileClick = {},
            selectedTabState = TabState.None,
            selectTab = {},
        )
    }
}