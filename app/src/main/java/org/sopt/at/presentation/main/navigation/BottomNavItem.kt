package org.sopt.at.presentation.main.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import org.sopt.at.R
import org.sopt.at.presentation.history.History
import org.sopt.at.presentation.home.Home
import org.sopt.at.presentation.live.Live
import org.sopt.at.presentation.search.Search
import org.sopt.at.presentation.shorts.Shorts

enum class BottomNavItem(
    @DrawableRes val selectedIconRes: Int,
    @DrawableRes val unselectedIconRes: Int,
    @StringRes val labelRes: Int,
    val route: Any
) {
    HOME(
        selectedIconRes = R.drawable.ic_nav_home_selected,
        unselectedIconRes = R.drawable.ic_nav_home_unselected,
        labelRes = R.string.nav_home,
        route = Home
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
    }
}