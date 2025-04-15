package com.ykcoding.recipefinderapp.presentation.view_state

import com.ykcoding.recipefinderapp.data.remote.dto.Result
import com.ykcoding.recipefinderapp.helper.EventHandler
import com.ykcoding.recipefinderapp.helper.NetworkResponse

data class HomeScreenUIState(
    val popularRecipes: SectionState = SectionState.IsLoading,
    val healthyRecipes: SectionState = SectionState.IsLoading,
    val quickRecipes: SectionState = SectionState.IsLoading,
    val randomRecipes: SectionState = SectionState.IsLoading,
) {
    sealed class SectionState {
        data object IsLoading: SectionState()
        data class Success(val body: List<Result>): SectionState()
        data class Error(val error: EventHandler<NetworkResponse.Error>): SectionState()
    }
}
