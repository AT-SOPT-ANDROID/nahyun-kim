package org.sopt.at.core.designsystem.theme

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat


private val defaultColorScheme = darkColorScheme(
    primary = BrandRed,
    onPrimary = OnPrimary,
    background = BasicBlack,
    onBackground = BasicWhite,
    primaryContainer = Gray5,
    onPrimaryContainer = Gray2
)

object TvingTheme {
    val colors: TvingColors
        @Composable
        @ReadOnlyComposable
        get() = LocalTvingColorsProvider.current
}

@Composable
fun ProvideTvingColors(
    colors: TvingColors,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalTvingColorsProvider provides colors,
        content = content
    )
}

@Composable
fun ATSOPTANDROIDTheme(
    content: @Composable () -> Unit
) {
    ProvideTvingColors(
        colors = defaultTvingColors
    ) {
        val view = LocalView.current
        if (!view.isInEditMode) {
            SideEffect {
                (view.context as Activity).window.run {
                    WindowCompat.getInsetsController(this, view).isAppearanceLightStatusBars = false
                }
            }
        }
        MaterialTheme(
            colorScheme = defaultColorScheme,
            typography = Typography,
            content = content
        )
    }
}