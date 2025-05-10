package org.sopt.at.core.designsystem.component.dialog

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import org.sopt.at.R
import org.sopt.at.core.designsystem.component.textfield.CommonTextField
import org.sopt.at.core.designsystem.theme.ATSOPTANDROIDTheme
import org.sopt.at.core.designsystem.theme.TvingTheme

@Composable
fun NicknameEditDialog(
    placeHolder: String,
    nickname: String,
    onNicknameChange: (String) -> Unit,
    onCancelClick: () -> Unit,
    onNicknameEditClick: () -> Unit
) {
    AlertDialog(
        containerColor = TvingTheme.colors.gray4,
        onDismissRequest = {
            onCancelClick()
        },
        title = {
            Text(
                text = stringResource(R.string.my_nickname_edit_title),
                textAlign = TextAlign.Center,
                style = TvingTheme.typography.title,
                modifier = Modifier.fillMaxWidth()
            )
        },
        text = {
            Column {
                CommonTextField(
                    placeholder = placeHolder,
                    value = nickname,
                    onValueChange = onNicknameChange
                )
            }
        },
        dismissButton = {
            TextButton(onClick = {
                onCancelClick()
            }) {
                Text(
                    text = stringResource(R.string.cancel),
                    color = TvingTheme.colors.basicWhite
                )
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onNicknameEditClick()
                }) {
                Text(
                    text = stringResource(R.string.confirm),
                    color = TvingTheme.colors.basicWhite
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun DialogPreview() {
    ATSOPTANDROIDTheme {
        NicknameEditDialog(
            onCancelClick = {},
            onNicknameEditClick = {},
            placeHolder = "nahyun",
            nickname = "",
            onNicknameChange = {}
        )
    }
}