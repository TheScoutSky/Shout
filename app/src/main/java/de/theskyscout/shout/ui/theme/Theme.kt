package de.theskyscout.shout.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = PrimaryDarkColor,
    onPrimary = onPrimaryDarkColor,
    secondary = SecondaryDarkColor,
    tertiary = TertiaryDarkColor,
    background = BackgroundDarkColor,
    surface = SurfaceDarkColor,
    onPrimaryContainer = onPrimaryContainerDarkColor
)

private val LightColorScheme = lightColorScheme(
    primary = PrimaryLightColor,
    onPrimary = onPrimaryLightColor,
    secondary = SecondaryLightColor,
    tertiary = TertiaryLightColor,
    background = BackgroundLightColor,
    surface = SurfaceLightColor,
    onPrimaryContainer = onPrimaryContainerLightColor
)

@Composable
fun ShoutTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}