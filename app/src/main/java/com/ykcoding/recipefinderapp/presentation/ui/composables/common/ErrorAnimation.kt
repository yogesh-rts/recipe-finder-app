package com.ykcoding.recipefinderapp.presentation.ui.composables.common

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition


@Composable
fun setErrorAnimation(
    modifier: Modifier,
    assetFileName: String
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.Asset(assetFileName))

    Box(
        modifier = Modifier,
    ) {
        LottieAnimation(
            composition,
            iterations = LottieConstants.IterateForever,
            modifier = modifier
        )
    }
}