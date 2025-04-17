package com.ykcoding.recipefinderapp.helper

import com.ykcoding.recipefinderapp.presentation.view_state.HomeScreenUIState

fun HomeScreenUIState.allSectionsFailedToLoad(): Boolean {
    return listOf(
        popularRecipes,
        healthyRecipes,
        quickRecipes,
        randomRecipes
    ).all { it is HomeScreenUIState.SectionState.Error }
}

fun HomeScreenUIState.allSectionsAreLoading(): Boolean {
    return listOf(
        popularRecipes,
        healthyRecipes,
        quickRecipes,
        randomRecipes
    ).all { it is HomeScreenUIState.SectionState.IsLoading }
}