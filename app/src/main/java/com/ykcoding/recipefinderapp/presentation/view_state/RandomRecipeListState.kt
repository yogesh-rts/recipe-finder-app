package com.ykcoding.recipefinderapp.presentation.view_state

import com.ykcoding.recipefinderapp.domain.model.RandomRecipes

data class RandomRecipeListState(
    val isLoading: Boolean = false,
    val result: RandomRecipes? = null,
    val error: String = ""
)
