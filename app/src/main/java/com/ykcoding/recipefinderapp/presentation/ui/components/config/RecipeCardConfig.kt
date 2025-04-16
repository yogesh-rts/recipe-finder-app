package com.ykcoding.recipefinderapp.presentation.ui.components.config

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class RecipeCardConfig(
    val cardAspectRatio: Float = 2f,
    val textSize: TextUnit = 14.sp,
    val imageWidth: Dp = 225.dp
)
