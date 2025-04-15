package com.ykcoding.recipefinderapp.presentation.view_state

import com.ykcoding.recipefinderapp.data.remote.dto.Result
import com.ykcoding.recipefinderapp.helper.EventHandler
import com.ykcoding.recipefinderapp.helper.NetworkResponse

data class HomeScreenUIState(
    val popularRecipes: List<Result> = emptyList(),
    val healthyRecipes:  List<Result> = emptyList(),
    val quickRecipes:  List<Result> = emptyList(),
    val randomRecipes:  List<Result> = emptyList(),
    val error: EventHandler<NetworkResponse.Error>? = null
)
