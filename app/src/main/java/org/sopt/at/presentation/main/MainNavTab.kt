package org.sopt.at.presentation.main

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import org.sopt.at.R
import org.sopt.at.presentation.history.History
import org.sopt.at.presentation.home.navigation.Home
import org.sopt.at.presentation.live.Live
import org.sopt.at.presentation.search.Search
import org.sopt.at.presentation.shorts.Shorts
import org.sopt.at.util.MainTabRoute

enum class MainNavTab(
    @DrawableRes val selectedIconRes: Int,
    @DrawableRes val unselectedIconRes: Int,
    @StringRes val labelRes: Int,
    val route: MainTabRoute
) {
    HOME(
        selectedIconRes = R.drawable.ic_nav_home_selected,
        unselectedIconRes = R.drawable.ic_nav_home_unselected,
        labelRes = R.string.nav_home,
        route = Home()
    ),
    SHORTS(
        selectedIconRes = R.drawable.ic_nav_shorts_selected,
        unselectedIconRes = R.drawable.ic_nav_shorts_unselected,
        labelRes = R.string.nav_shorts,
        route = Shorts
    ),
    LIVE(
        selectedIconRes = R.drawable.ic_nav_live_selected,
        unselectedIconRes = R.drawable.ic_nav_live_unselected,
        labelRes = R.string.nav_live,
        route = Live
    ),
    SEARCH(
        selectedIconRes = R.drawable.ic_nav_search_selected,
        unselectedIconRes = R.drawable.ic_nav_search_unselected,
        labelRes = R.string.nav_search,
        route = Search
    ),
    HISTORY(
        selectedIconRes = R.drawable.ic_nav_history_selected,
        unselectedIconRes = R.drawable.ic_nav_history_unselected,
        labelRes = R.string.nav_history,
        route = History
    );

    companion object {
        fun getTabItems() = entries

        @Composable
        fun find(predicate: @Composable (MainTabRoute) -> Boolean): MainNavTab? {
            return entries.find { predicate(it.route) }
        }

        @Composable
        fun contains(predicate: @Composable (MainTabRoute) -> Boolean): Boolean {
            return entries.map { it.route }.any { predicate(it) }
        }
    }
}