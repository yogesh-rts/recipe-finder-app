package com.ykcoding.recipefinderapp.presentation.ui.components.config

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

object RecipeCardPresets {

    val FEATURED = RecipeCardConfig(
        cardAspectRatio = 2f,
        textSize = 16.sp,
        imageWidth = 275.dp
    )

    val LARGE = RecipeCardConfig(
        cardAspectRatio = 1.75f,
        textSize = 14.sp,
        imageWidth = 240.dp
    )

    val COMPACT = RecipeCardConfig(
        cardAspectRatio = 1.5f,
        textSize = 12.sp,
        imageWidth = 200.dp
    )

    val SMALL = RecipeCardConfig(
        cardAspectRatio = 1.3f,
        textSize = 11.sp,
        imageWidth = 160.dp
    )

}