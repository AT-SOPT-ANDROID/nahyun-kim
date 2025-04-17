package org.sopt.at.presentation.home

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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.at.R
import org.sopt.at.ui.common.appbar.CommonTopAppBar
import org.sopt.at.ui.common.button.ButtonSizeType
import org.sopt.at.ui.common.button.CommonOutlinedButton
import org.sopt.at.presentation.auth.login.LoginActivity
import org.sopt.at.presentation.auth.login.LoginActivity.Companion.USER_ID_KEY
import org.sopt.at.ui.theme.ATSOPTANDROIDTheme
import org.sopt.at.ui.theme.ButtonTint
import org.sopt.at.ui.theme.White

//TODO: 바텀네비 구현 후 Screen으로 바꾸기
class MyActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ATSOPTANDROIDTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        CommonTopAppBar(
                            onBackClick = {
                                finish()
                            },
                            trailingIcon = {
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
                            },
                        )
                    }
                ) { innerPadding ->
                    MyScreen(
                        modifier = Modifier.padding(innerPadding),
                        userName = intent.getStringExtra(USER_ID_KEY).toString(),
                        onNextClick = {
                            val intent = Intent(this, LoginActivity::class.java).apply {
                                flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                            }
                            startActivity(intent)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun MyScreen(
    modifier: Modifier = Modifier,
    userName: String,
    onNextClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(
                vertical = dimensionResource(R.dimen.screen_padding_vertical),
                horizontal = dimensionResource(R.dimen.screen_padding_horizontal)
            ),
        verticalArrangement = Arrangement.Top,
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

                IconButton(onClick = {
                    //TODO: 상위에서 onProfileEditClick 정의하기
                }) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        tint = ButtonTint,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
            CommonOutlinedButton(
                modifier = Modifier.height(30.dp),
                sizeType = ButtonSizeType.SMALL,
                textResId = R.string.my_profile_switch,
                onClick = {
                    //TODO: 상위에서 onProfileSwitchClick 정의하기
                }
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        CommonOutlinedButton(
            modifier = Modifier,
            sizeType = ButtonSizeType.LARGE,
            textResId = R.string.my_logout,
            onClick = onNextClick
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MyPreview() {
    ATSOPTANDROIDTheme {
        MyScreen(userName = "김나현", onNextClick = {})
    }
}