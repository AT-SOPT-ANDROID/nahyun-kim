package org.sopt.at.ui.screens.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.at.R
import org.sopt.at.ui.components.button.ArrowBackIcon
import org.sopt.at.ui.components.button.LargeOutlinedButton
import org.sopt.at.ui.components.button.SmallOutlinedButton
import org.sopt.at.ui.screens.login.LoginActivity
import org.sopt.at.ui.screens.login.LoginActivity.Companion.USER_ID_KEY
import org.sopt.at.ui.theme.ATSOPTANDROIDTheme
import org.sopt.at.ui.theme.ButtonTint
import org.sopt.at.ui.theme.White

//TODO: 바텀네비 구현 후 Screen으로 바꾸기
class MyActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ATSOPTANDROIDTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { },
                            navigationIcon = {
                                IconButton(onClick = {}) {
                                    ArrowBackIcon()
                                }
                            },
                            colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent),
                            actions = {
                                IconButton(onClick = {}) {
                                    Icon(
                                        imageVector = Icons.Outlined.Notifications,
                                        tint = White,
                                        contentDescription = null
                                    )
                                }
                                IconButton(onClick = {}) {
                                    Icon(
                                        imageVector = Icons.Outlined.Settings,
                                        tint = White,
                                        contentDescription = null
                                    )
                                }
                            }
                        )
                    },
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    MyScreen(
                        modifier = Modifier.padding(innerPadding),
                        userName = intent.getStringExtra(USER_ID_KEY).toString()
                    )
                }
            }
        }
    }
}

@Composable
fun MyScreen(
    modifier: Modifier = Modifier,
    userName: String = ""
) {
    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(
                vertical = dimensionResource(R.dimen.screen_padding_vertical),
                horizontal = dimensionResource(R.dimen.screen_padding_horizontal)
            ),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.Start
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(R.drawable.img_profile),
                    contentDescription = null,
                    modifier = Modifier.size(80.dp)
                )

                Spacer(modifier = Modifier.width(10.dp))

                Text(
                    text = userName,
                    style = MaterialTheme.typography.titleMedium
                )

                IconButton(onClick = {}) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        tint = ButtonTint,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
            SmallOutlinedButton(
                modifier = Modifier.height(30.dp),
                buttonTextRes = R.string.my_profile_switch,
                onClick = {}
            )
        }

        LargeOutlinedButton(
            modifier = Modifier,
            buttonTextRes = R.string.my_logout,
            onClick = {
                val intent = Intent(context, LoginActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                }
                context.startActivity(intent)
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MyPreview() {
    ATSOPTANDROIDTheme {
        MyScreen()
    }
}