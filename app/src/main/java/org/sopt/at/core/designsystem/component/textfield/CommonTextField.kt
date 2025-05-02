package org.sopt.at.core.designsystem.component.textfield

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.sopt.at.R
import org.sopt.at.core.designsystem.theme.ATSOPTANDROIDTheme
import org.sopt.at.core.designsystem.theme.TvingTheme

enum class TextFieldType() {
    DEFAULT, PASSWORD
}

@Composable
fun CommonTextField(
    modifier: Modifier = Modifier,
    type: TextFieldType = TextFieldType.DEFAULT,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    placeholder: String,
    value: String,
    height: Dp = 48.dp,
    onValueChange: (String) -> Unit,
) {

    val interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()
    var isPwdVisible by remember { mutableStateOf(false) }
    val pwdIconId = if (isPwdVisible) R.drawable.ic_password_show else R.drawable.ic_password_hide
    val isPwdTextField = (type == TextFieldType.PASSWORD)

    val borderLineColor = when {
        isFocused -> TvingTheme.colors.buttonDisableText
        else -> Color.Transparent
    }

    BasicTextField(
        modifier = modifier.fillMaxWidth().height(height),
        value = value,
        onValueChange = onValueChange,
        keyboardOptions = keyboardOptions,
        visualTransformation = if (!isPwdVisible && isPwdTextField) PasswordVisualTransformation() else VisualTransformation.None,
        interactionSource = interactionSource,
        singleLine = true,
        textStyle = TvingTheme.typography.label.copy(
            color = TvingTheme.colors.onPrimary
        ),
        cursorBrush = SolidColor(TvingTheme.colors.onPrimary),
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(dimensionResource(R.dimen.button_radius)))
                    .background(color = TvingTheme.colors.primaryContainer)
                    .border(border = BorderStroke(dimensionResource(R.dimen.outline_button_stroke_width), borderLineColor), shape = RoundedCornerShape(dimensionResource(R.dimen.button_radius)))
                    .fillMaxSize()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(
                    modifier = Modifier.weight(1f)
                ) {
                    if (value.isEmpty()) {
                        Text(
                            text = placeholder,
                            style = TvingTheme.typography.label.copy(
                                color = TvingTheme.colors.hintText
                            )
                        )
                    }
                    innerTextField()
                }
                if (isPwdTextField) {
                    IconButton(onClick = { isPwdVisible = !isPwdVisible }) {
                        Icon(
                            modifier = Modifier.size(20.dp),
                            imageVector = ImageVector.vectorResource(pwdIconId),
                            tint = TvingTheme.colors.iconTint,
                            contentDescription = "password",
                        )
                    }
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun CommonTextFieldPreview() {
    ATSOPTANDROIDTheme {
        var text by remember { mutableStateOf("") }

        CommonTextField(
            modifier = Modifier.fillMaxWidth(),
            placeholder = "placeHolder",
            value = text,
            onValueChange = {text = it},
        )
    }
}