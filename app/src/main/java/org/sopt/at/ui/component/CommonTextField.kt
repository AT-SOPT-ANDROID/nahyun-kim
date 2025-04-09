//package org.sopt.at.ui.component
//
//import android.R.attr.minHeight
//import android.R.attr.shape
//import androidx.compose.foundation.background
//import androidx.compose.foundation.border
//import androidx.compose.foundation.interaction.MutableInteractionSource
//import androidx.compose.foundation.interaction.collectIsFocusedAsState
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.heightIn
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.foundation.text.BasicTextField
//import androidx.compose.foundation.text.input.TextFieldState
//import androidx.compose.material3.*
//import androidx.compose.material3.TextFieldDefaults.indicatorLine
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.remember
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.text.input.VisualTransformation
//import androidx.compose.ui.text.style.TextOverflow
//import androidx.compose.ui.unit.dp
//import org.sopt.at.ui.theme.OnBackground
//import org.sopt.at.ui.theme.OnPrimaryContainer
//import org.sopt.at.ui.theme.PrimaryContainer
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun CommonTextField(
//    modifier: Modifier = Modifier,
//    placeholder: String = "",
//    value: String = "",
//    onValueChange: (String) -> Unit = { _ -> },
//    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
//) {
//    val isFocused by interactionSource.collectIsFocusedAsState()
//
//    val borderLineColor = when {
//        isFocused -> OnBackground
//        else -> Color.Transparent
//    }
//
//    BasicTextField(
//        state = TextFieldState,
//        value = value,
//        onValueChange = onValueChange,
//        placeholder = placeholder,
//        modifier = modifier
//            .background(
//                color = PrimaryContainer,
//                shape = RoundedCornerShape(8.dp)
//            )
//            .indicatorLine(
//                enabled = true,
//                isError = false,
//                interactionSource = interactionSource,
//                colors = TextFieldDefaults.colors(
//                    PrimaryContainer
//                )
//            )
//            .fillMaxWidth(),
//        interactionSource = interactionSource,
//        decorationBox = { innerText ->
//            Column(
//                modifier = Modifier
//                    .fillMaxWidth(),
//                verticalArrangement = Arrangement.Center,
//            ) {
//                Box(
//                    modifier = Modifier
//                        .heightIn(minHeight)
//                        .fillMaxWidth()
//                        .clip(shape = shape)
//                        .background(color = PrimaryContainer)
//                        .border(
//                            width = 1.dp,
//                            color = borderLineColor,
//                            shape = shape,
//                        )
//                        .padding(vertical = 16.dp, horizontal = 18.dp),
//                ) {
//                    Row(
//                        modifier = Modifier.fillMaxWidth(),
//                        verticalAlignment = Alignment.CenterVertically,
//                    ) {
//                        Box(
//                            modifier = Modifier.weight(1f)
//                        ) {
//                            if (value.isEmpty()) {
//                                Text(
//                                    text = placeholder,
//                                    color = OnPrimaryContainer,
//                                    maxLines = 1,
//                                    overflow = TextOverflow.Clip,
//                                )
//                            }
////                            innerText()
//                        }
//                    }
//                }
//            }
//        }
//    )
//}