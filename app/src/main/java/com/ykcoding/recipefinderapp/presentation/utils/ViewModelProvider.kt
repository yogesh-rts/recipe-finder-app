package com.ykcoding.recipefinderapp.presentation.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.lifecycle.ViewModel


class ViewModelHolder <T: ViewModel>(val viewModel: T)

val LocalViewModelHolder = staticCompositionLocalOf<ViewModelHolder<out ViewModel>> {
    error("ViewModel not provided")
}

@Composable
inline fun <reified T : ViewModel> ProvideViewModel(
    viewModel: T,
    crossinline content: @Composable () -> Unit
) {
    val holder = ViewModelHolder(viewModel)
    CompositionLocalProvider(LocalViewModelHolder provides holder) {
        content()
    }
}

@Composable
inline fun <reified T : ViewModel> getProvidedViewModel(): T {
    return LocalViewModelHolder.current.viewModel as T
}

