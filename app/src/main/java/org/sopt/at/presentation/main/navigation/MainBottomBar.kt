package org.sopt.at.presentation.main.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
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
import org.sopt.at.util.DisableRippleEffect

@Composable
fun MainBottomBar(
    navController: NavHostController
) {
    val screens = BottomNavItem.getTabItems()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Row(
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .background(Color.Transparent)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
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
    navController: androidx.navigation.NavController
) {
    val selected = currentDestination?.hierarchy?.any {
        it.route.hashCode() == item.route.hashCode()
    } == true

    val contentColor = if (selected) White else ButtonDisableText

    DisableRippleEffect {
        Box(
            modifier = Modifier
                .clickable(onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.findStartDestination().id)
                        launchSingleTop = true
                    }
                })
                .weight(1f)
        ) {
            Column(
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = if (selected) item.selectedIconRes else item.unselectedIconRes),
                    contentDescription = "icon",
                    tint = contentColor,
                    modifier = Modifier.size(30.dp)
                )
                Text(
                    text = stringResource(item.labelRes),
                    color = contentColor,
                    fontSize = 10.sp,
                    style = TextStyle(
                        platformStyle = PlatformTextStyle(
                            includeFontPadding = false
                        ),
                    ),
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MainBottomBarPreview() {
    ATSOPTANDROIDTheme {
        val navController = rememberNavController()
        MainBottomBar(navController)
    }
}