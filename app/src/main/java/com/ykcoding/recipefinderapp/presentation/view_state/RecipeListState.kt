package com.ykcoding.recipefinderapp.presentation.view_state

import com.ykcoding.recipefinderapp.domain.model.Recipes

data class RecipeListState(
    val isLoading: Boolean = false,
    val result: Recipes? = null,
    val error: String = ""
)
