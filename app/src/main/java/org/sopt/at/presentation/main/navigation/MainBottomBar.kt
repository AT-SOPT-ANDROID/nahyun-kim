package org.sopt.at.presentation.main.navigation

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import org.sopt.at.ui.theme.ButtonDisableText
import org.sopt.at.ui.theme.White

@Composable
fun MainBottomBar(
    navController: NavHostController
) {
    val screens = BottomNavItem.getTabItems()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigation(
        backgroundColor = Color.Transparent
    ) {
        screens.forEach { screen ->
            TabItem(
                item = screen,
                currentDestination = currentDestination,
                navController = navController
            )
        }
    }
}

@Composable
fun RowScope.TabItem(
    item: BottomNavItem,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    val selected = currentDestination?.hierarchy?.any {
        it.route == item.route
    } == true

    BottomNavigationItem(
        label = { Text(text = item.label, fontSize = 10.sp) },
        selected = selected,
        icon = {
            Icon(
                imageVector = ImageVector.vectorResource(
                    id = if (selected) item.selectedIconResource else item.unselectedIconResource
                ),
                contentDescription = item.label,
            )
        },
        selectedContentColor = White,
        unselectedContentColor = ButtonDisableText,
        onClick = {
            navController.navigate(item.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        }
    )
}