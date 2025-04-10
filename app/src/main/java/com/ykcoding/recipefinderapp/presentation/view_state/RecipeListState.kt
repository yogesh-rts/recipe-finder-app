package com.ykcoding.recipefinderapp.presentation.view_state

import com.ykcoding.recipefinderapp.domain.model.Recipes
import com.ykcoding.recipefinderapp.helper.EventHandler
import com.ykcoding.recipefinderapp.helper.NetworkResponse

data class RecipeListState(
    val isLoading: Boolean = false,
    val result: Recipes? = null,
    val error: EventHandler<NetworkResponse.Error>? = null
)
