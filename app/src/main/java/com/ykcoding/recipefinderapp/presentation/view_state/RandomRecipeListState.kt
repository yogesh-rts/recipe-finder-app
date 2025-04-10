package com.ykcoding.recipefinderapp.presentation.view_state

import com.ykcoding.recipefinderapp.domain.model.RandomRecipes
import com.ykcoding.recipefinderapp.helper.EventHandler
import com.ykcoding.recipefinderapp.helper.NetworkResponse

data class RandomRecipeListState(
    val isLoading: Boolean = false,
    val result: RandomRecipes? = null,
    val error: EventHandler<NetworkResponse.Error>? = null
)
