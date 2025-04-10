package com.ykcoding.recipefinderapp.presentation.ui.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ykcoding.recipefinderapp.domain.usecases.RandomRecipesUseCase
import com.ykcoding.recipefinderapp.helper.EventHandler
import com.ykcoding.recipefinderapp.helper.NetworkResponse
import com.ykcoding.recipefinderapp.presentation.view_state.RandomRecipeListState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class HomeScreenViewModel(
    private val randomRecipesUseCase: RandomRecipesUseCase
): ViewModel() {

    private val _randomRecipes = MutableStateFlow(RandomRecipeListState())
    val randomRecipes = _randomRecipes.asStateFlow()

    init {
        showRandomRecipes()
    }

    private fun showRandomRecipes() {
        _randomRecipes.value = RandomRecipeListState(isLoading = true)
        randomRecipesUseCase.invoke().onEach { response ->
            when(response) {
                is NetworkResponse.Success -> {
                    _randomRecipes.value = RandomRecipeListState(
                        isLoading = false,
                        result = response.body
                    )
                }
                is NetworkResponse.Error -> {
                    _randomRecipes.value = RandomRecipeListState(
                        isLoading = false,
                        error = EventHandler(response)
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

}