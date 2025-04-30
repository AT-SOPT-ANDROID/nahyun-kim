package org.sopt.at.presentation.main.component

import androidx.compose.animation.AnimatedVisibility
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
import org.sopt.at.presentation.main.MainNavTab
import org.sopt.at.ui.theme.ATSOPTANDROIDTheme
import org.sopt.at.ui.theme.ButtonDisableText
import org.sopt.at.ui.theme.White
import org.sopt.at.util.DisableRippleEffect

@Composable
fun MainBottomBar(
    visible: Boolean,
    currentTab: MainNavTab?,
    onTabSelected: (MainNavTab) -> Unit,
) {
    val tabs = MainNavTab.getTabItems()

    AnimatedVisibility(
        visible = visible
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .background(Color.Transparent)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            tabs.forEach { tab ->
                TabItem(
                    item = tab,
                    selected = (tab == currentTab),
                    onTabClick = { onTabSelected(tab) },
                )
            }
        }
    }
}

@Composable
fun RowScope.TabItem(
    item: MainNavTab,
    selected: Boolean,
    onTabClick: () -> Unit
) {
    val contentColor = if (selected) White else ButtonDisableText

    DisableRippleEffect {
        Box(
            modifier = Modifier
                .clickable(onClick = onTabClick)
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
        MainBottomBar(
            visible = true,
            currentTab = null,
            onTabSelected = {}
        )
    }
}