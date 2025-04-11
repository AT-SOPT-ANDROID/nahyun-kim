package org.sopt.at.ui.components.textfield

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.sopt.at.R
import org.sopt.at.ui.theme.ATSOPTANDROIDTheme
import org.sopt.at.ui.theme.ButtonDisableText
import org.sopt.at.ui.theme.HintText
import org.sopt.at.ui.theme.TextFieldBg
import org.sopt.at.ui.theme.White

enum class TextFieldType() {
    DEFAULT, PASSWORD
}

@Composable
fun CommonTextField(
    modifier: Modifier = Modifier,
    type: TextFieldType = TextFieldType.DEFAULT,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    placeholder: String = "",
    value: String = "",
    height: Dp = 48.dp,
    onValueChange: (String) -> Unit = { },
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
) {
    val isFocused by interactionSource.collectIsFocusedAsState()
    var isPwdVisible by remember { mutableStateOf(false) }
    val pwdIcon = if (isPwdVisible) painterResource(R.drawable.ic_password_show) else painterResource(R.drawable.ic_password_hide)
    val isPwdTextField = (type == TextFieldType.PASSWORD)

    val borderLineColor = when {
        isFocused -> ButtonDisableText
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
        textStyle = MaterialTheme.typography.bodyMedium.copy(
            color = White
        ),
        cursorBrush = SolidColor(White),
        decorationBox = { innerTextField ->
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .background(
                        color = TextFieldBg,
                        shape = RoundedCornerShape(dimensionResource(R.dimen.button_radius))
                    )
                    .border(
                        border = BorderStroke(dimensionResource(R.dimen.outline_button_stroke_width), borderLineColor),
                        shape = RoundedCornerShape(dimensionResource(R.dimen.button_radius))
                    )
                    .fillMaxSize()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
            ) {
                Box(
                    modifier = Modifier.weight(1f)
                ) {
                    if (value.isEmpty()) {
                        Text(
                            text = placeholder,
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = HintText
                            )
                        )
                    }
                    innerTextField()
                }
                if (isPwdTextField) {
                    IconButton(onClick = { isPwdVisible = !isPwdVisible }) {
                        Icon(
                            painter = pwdIcon,
                            tint = ButtonDisableText,
                            contentDescription = null,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    ATSOPTANDROIDTheme {
        CommonTextField(modifier = Modifier.fillMaxWidth())
    }
}