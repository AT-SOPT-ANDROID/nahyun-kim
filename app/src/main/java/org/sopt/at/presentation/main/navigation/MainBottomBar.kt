package org.sopt.at.presentation.main.navigation

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.sopt.at.ui.theme.ATSOPTANDROIDTheme
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
        it.route.hashCode() == item.route.hashCode()
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
                modifier = Modifier.size(30.dp)
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

@Preview(showBackground = true)
@Composable
private fun MainBottomBarPreview() {
    ATSOPTANDROIDTheme {
        val navController = rememberNavController()
        MainBottomBar(navController)
    }
}