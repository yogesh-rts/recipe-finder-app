package com.ykcoding.recipefinderapp.presentation.ui.theme

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.core.view.WindowInsetsControllerCompat

val projectColorScheme = lightColorScheme(
    primary = FreshRed,
    secondary = AvocadoGreen,
    tertiary = CitrusOrange,
    background = Concrete ,
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
    val context = LocalContext.current
    val window = (context as? Activity)?.window
    window?.let {
        val controller = WindowInsetsControllerCompat(it, it.decorView)
        controller.isAppearanceLightStatusBars = true
    }

    MaterialTheme(
        colorScheme = projectColorScheme,
        typography = Typography,
        content = content
    )
}