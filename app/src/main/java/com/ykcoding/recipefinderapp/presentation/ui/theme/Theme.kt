package com.ykcoding.recipefinderapp.presentation.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val projectColorScheme = lightColorScheme(
    primary = FreshRed,
    secondary = AvocadoGreen,
    tertiary = CitrusOrange,
    background = OnionPinkLighter ,
    surface = SoftIvoryCream,
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