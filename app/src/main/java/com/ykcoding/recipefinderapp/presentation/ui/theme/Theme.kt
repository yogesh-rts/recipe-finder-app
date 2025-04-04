package com.ykcoding.recipefinderapp.presentation.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40
)

val FreshRed = Color(0xFFD32F2F)
val AvocadoGreen = Color(0xFF388E3C)
val EmeraldGreen = Color(0xFF0A470A)
val CitrusOrange = Color(0xFFFF9800)
val WarmBeige = Color(0xFFF5F5F5)
val CharcoalBlack = Color(0xFF212121)
val GoldenMangoYellow = Color(0xFFFFEB3B)
val ChocoBrown = Color(0xFF2F1D01)

val projectColorScheme = lightColorScheme(
    primary = FreshRed,
    secondary = AvocadoGreen,
    tertiary = CitrusOrange,
    background = WarmBeige,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = CharcoalBlack,
    onSurface = CharcoalBlack,
    onTertiary = CharcoalBlack
)

@Composable
fun RecipeFinderAppTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = projectColorScheme,
        typography = Typography,
        content = content
    )
}