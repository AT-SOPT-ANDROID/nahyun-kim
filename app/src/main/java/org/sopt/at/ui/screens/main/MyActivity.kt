package org.sopt.at.ui.screens.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.at.ui.screens.login.ArrowBackIcon
import org.sopt.at.ui.screens.login.LoginActivity
import org.sopt.at.ui.screens.login.LoginActivity.Companion.USER_ID_KEY
import org.sopt.at.R
import org.sopt.at.ui.theme.ATSOPTANDROIDTheme
import org.sopt.at.ui.theme.ButtonDisableText
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
            .padding(horizontal = 16.dp),
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

            OutlinedButton( // 프로필 전환
                onClick = {},
                contentPadding = PaddingValues(horizontal = 16.dp),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .border(
                        width = 0.5.dp,
                        color = Color.Transparent,
                        shape = RoundedCornerShape(8.dp)
                    ),
            ) {
                Text(
                    text = stringResource(R.string.my_profile_switch),
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.ExtraBold,
                )
            }
        }

        OutlinedButton( // 다음 버튼
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp)
                .border(
                    width = 0.5.dp,
                    color = ButtonDisableText,
                    shape = RoundedCornerShape(8.dp)
                ),
            onClick = {
                val intent = Intent(context, LoginActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                }
                context.startActivity(intent)
            },
            shape = RoundedCornerShape(8.dp),
            contentPadding = PaddingValues(vertical = 12.dp)

        ) {
            Text(
                text = stringResource(R.string.my_logout),
                style = MaterialTheme.typography.labelLarge,
                color = ButtonDisableText
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MyPreview() {
    ATSOPTANDROIDTheme {
        MyScreen()
    }
}