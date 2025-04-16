package org.sopt.at.ui.screens.main.navigation

import androidx.annotation.DrawableRes
import org.sopt.at.R
import org.sopt.at.ui.screens.main.History
import org.sopt.at.ui.screens.main.Home
import org.sopt.at.ui.screens.main.Live
import org.sopt.at.ui.screens.main.Search
import org.sopt.at.ui.screens.main.Shorts

enum class BottomNavItem(
    @DrawableRes val selectedIconResource: Int,
    @DrawableRes val unselectedIconResource: Int,
    val label: String,
    val route: Any
) {
    HOME(
        selectedIconResource = R.drawable.ic_nav_home_selected,
        unselectedIconResource = R.drawable.ic_nav_home_unselected,
        label = "HOME",
        route = Home
    ),
    SHORTS(
        selectedIconResource = R.drawable.ic_nav_shorts_selected,
        unselectedIconResource = R.drawable.ic_nav_shorts_unselected,
        label = "Shorts",
        route = Shorts
    ),
    LIVE(
        selectedIconResource = R.drawable.ic_nav_live_selected,
        unselectedIconResource = R.drawable.ic_nav_live_unselected,
        label = "LIVE",
        route = Live
    ),
    SEARCH(
        selectedIconResource = R.drawable.ic_nav_search_selected,
        unselectedIconResource = R.drawable.ic_nav_search_unselected,
        label = "SEARCH",
        route = Search
    ),
    HISTORY(
        selectedIconResource = R.drawable.ic_nav_history_selected,
        unselectedIconResource = R.drawable.ic_nav_history_unselected,
        label = "HISTORY",
        route = History
    );

    companion object {
        fun getTabItems() = entries
    }
}