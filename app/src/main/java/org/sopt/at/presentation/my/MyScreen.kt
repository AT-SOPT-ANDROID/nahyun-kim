package org.sopt.at.presentation.my

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.sopt.at.R
import org.sopt.at.core.designsystem.component.appbar.CommonTopAppBar
import org.sopt.at.core.designsystem.component.button.ButtonSizeType
import org.sopt.at.core.designsystem.component.button.CommonOutlinedButton
import org.sopt.at.core.designsystem.component.dialog.NicknameEditDialog
import org.sopt.at.core.designsystem.theme.ATSOPTANDROIDTheme
import org.sopt.at.core.designsystem.theme.TvingTheme
import org.sopt.at.core.state.UiState

@Composable
fun MyRoute(
    paddingValues: PaddingValues,
    navigateBack: () -> Unit,
    navigateToSignIn: () -> Unit,
    viewModel: MyViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val profileEditState by viewModel.profileEditState.collectAsStateWithLifecycle()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val context = LocalContext.current

    var dialogState by remember {
        mutableStateOf(false)
    }

    // 다이얼로그 표시
    if (dialogState) {
        NicknameEditDialog(
            placeHolder = state.nickname,
            nickname = profileEditState.nickname,
            onNicknameChange = viewModel::updateNickname,
            onCancelClick = {
                viewModel.updateNickname(state.nickname) // 입력 닉네임 초기화
                dialogState = false
            },
            onNicknameEditClick = viewModel::onNicknameEdit
        )
    }

    LaunchedEffect(uiState) {
        when (uiState) {
            is UiState.Success -> {
                Toast.makeText(context, "닉네임 수정 완료", Toast.LENGTH_SHORT).show()
                dialogState = false
            }
            is UiState.Error -> {
                Toast.makeText(context, (uiState as UiState.Error).message, Toast.LENGTH_SHORT).show()
            }
            else -> {}
        }
    }

    MyScreen(
        paddingValues = paddingValues,
        userId = state.nickname,
        onBackClick = navigateBack,
        onProfileEditClick = {
            dialogState = true
        },
        onLogoutClick = {
            viewModel.clearUserInfo() // 유저 정보 삭제
            navigateToSignIn()
        }
    )
}

@Composable
fun MyScreen(
    paddingValues: PaddingValues,
    userId: String,
    onBackClick: () -> Unit,
    onProfileEditClick: () -> Unit,
    onLogoutClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .background(TvingTheme.colors.background)
            .padding(paddingValues)
            .padding(
                vertical = dimensionResource(R.dimen.screen_padding_vertical),
                horizontal = dimensionResource(R.dimen.screen_padding_horizontal)
            )
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
    ) {
        CommonTopAppBar(
            modifier = Modifier,
            onBackClick = onBackClick,
            trailingIcon = {
                IconButton(onClick = {}) {
                    Icon(
                        imageVector = Icons.Outlined.Notifications,
                        tint = TvingTheme.colors.onPrimary,
                        contentDescription = null
                    )
                }
                IconButton(onClick = {}) {
                    Icon(
                        imageVector = Icons.Outlined.Settings,
                        tint = TvingTheme.colors.onPrimary,
                        contentDescription = null
                    )
                }
            },
        )
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
                    text = userId,
                    style = TvingTheme.typography.subTitle,
                    color = TvingTheme.colors.basicWhite
                )

                IconButton(onClick = { onProfileEditClick() }) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        tint = TvingTheme.colors.iconTint,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
            CommonOutlinedButton(
                modifier = Modifier.height(30.dp),
                sizeType = ButtonSizeType.SMALL,
                textResId = R.string.my_profile_switch,
                onClick = {}
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        CommonOutlinedButton(
            modifier = Modifier,
            sizeType = ButtonSizeType.LARGE,
            textResId = R.string.my_logout,
            onClick = onLogoutClick
        )
    }
}

@Preview
@Composable
private fun MyPreview() {
    ATSOPTANDROIDTheme {
        MyScreen(
            paddingValues = PaddingValues(),
            userId = "nahyun",
            onBackClick = {},
            onLogoutClick = {},
            onProfileEditClick = {}
        )
    }
}