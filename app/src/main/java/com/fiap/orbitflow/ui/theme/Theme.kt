package com.fiap.orbitflow.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = OrbitCyan,
    secondary = OrbitPurple,
    tertiary = OrbitOrange,
    background = SpaceBackground,
    surface = DeepSpace,
    surfaceVariant = OrbitSurface,
    onPrimary = SpaceBackground,
    onSecondary = OrbitTextPrimary,
    onTertiary = SpaceBackground,
    onBackground = OrbitTextPrimary,
    onSurface = OrbitTextPrimary,
    onSurfaceVariant = OrbitTextSecondary,
    error = OrbitRed
)

private val LightColorScheme = lightColorScheme(
    primary = OrbitBlue,
    secondary = OrbitPurple,
    tertiary = OrbitOrange,
    background = OrbitTextPrimary,
    surface = OrbitTextPrimary,
    surfaceVariant = ColorForLightSurface,
    onPrimary = OrbitTextPrimary,
    onSecondary = OrbitTextPrimary,
    onTertiary = SpaceBackground,
    onBackground = DeepSpace,
    onSurface = DeepSpace,
    onSurfaceVariant = DeepSpace,
    error = OrbitRed
)

@Composable
fun OrbitFlowTheme(
    darkTheme: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
