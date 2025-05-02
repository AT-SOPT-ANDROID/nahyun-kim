package org.sopt.at.core.designsystem.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val BrandRed = Color(0xFFFF153E)
val OnPrimary = Color(0xFFFFFFFF)
val BasicBlack = Color(0xFF000000)
val BasicWhite = Color(0xFFFFFFFF)
val Gray1 = Color(0xFFAAAAAA)
val Gray2 = Color(0xFF888888)
val Gray3 = Color(0xFF666666)
val Gray4 = Color(0xFF444444)
val Gray5 = Color(0xFF222222)

@Immutable
data class TvingColors(
    val basicBlack: Color,
    val basicWhite: Color,
    val gray1: Color,
    val gray2: Color,
    val gray3: Color,
    val gray4: Color,
    val gray5: Color,

    // 시스템 토큰
    val primary: Color,
    val onPrimary: Color,
    val primaryContainer: Color,
    val background: Color,
    val hintText: Color,
    val guideText: Color,
    val buttonDisableBg: Color,
    val buttonDisableText: Color,
    val labelButtonText: Color,
    val iconTint: Color
)

val defaultTvingColors = TvingColors(
    basicBlack = BasicBlack,
    basicWhite = BasicWhite,
    gray1 = Gray1,
    gray2 = Gray2,
    gray3 = Gray3,
    gray4 = Gray4,
    gray5 = Gray5,
    primary = BrandRed,
    onPrimary = BasicWhite,
    primaryContainer = Gray5,
    background = BasicBlack,
    hintText = Gray2,
    guideText = Gray2,
    buttonDisableBg = Gray4,
    buttonDisableText = Gray3,
    labelButtonText = Gray1,
    iconTint = Gray3,
)

val LocalTvingColorsProvider = staticCompositionLocalOf { defaultTvingColors }