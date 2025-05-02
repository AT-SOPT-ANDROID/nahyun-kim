package org.sopt.at.core.designsystem.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import org.sopt.at.R

val tvingBold = FontFamily(Font(R.font.pretendard_bold))
val tvingSemiBold = FontFamily(Font(R.font.pretendard_semibold))
val tvingRegular = FontFamily(Font(R.font.pretendard_regular))

@Immutable
data class TivingTypography(
    val title: TextStyle,
    val subTitle: TextStyle,
    val body: TextStyle,
    val button: TextStyle,
    val labelButton: TextStyle,
    val label: TextStyle,
    val caption: TextStyle,
)

val defaultTvingTypography = TivingTypography(
    title = TextStyle(
        fontFamily = tvingBold,
        fontSize = 26.sp,
        lineHeight = 32.sp
    ),
    subTitle = TextStyle(
        fontFamily = tvingSemiBold,
        fontSize = 18.sp,
        lineHeight = 26.sp
    ),
    body = TextStyle(
        fontFamily = tvingRegular,
        fontSize = 16.sp,
        lineHeight = 24.sp
    ),
    button = TextStyle(
        fontFamily = tvingBold,
        fontSize = 14.sp,
        lineHeight = 20.sp
    ),
    labelButton = TextStyle(
        fontFamily = tvingRegular,
        fontSize = 15.sp,
        lineHeight = 20.sp
    ),
    label = TextStyle(
        fontFamily = tvingRegular,
        fontSize = 14.sp,
        lineHeight = 20.sp
    ),
    caption = TextStyle(
        fontFamily = tvingRegular,
        fontSize = 12.sp,
        lineHeight = 16.sp
    ),
)

val LocalTvingTypographyProvider = staticCompositionLocalOf { defaultTvingTypography }