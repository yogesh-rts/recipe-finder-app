package com.ykcoding.recipefinderapp.presentation.ui.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ykcoding.recipefinderapp.data.remote.dto.Result
import com.ykcoding.recipefinderapp.domain.model.RandomRecipes
import com.ykcoding.recipefinderapp.domain.model.Recipes
import com.ykcoding.recipefinderapp.domain.usecases.RandomRecipesUseCase
import com.ykcoding.recipefinderapp.domain.usecases.RecipesUseCase
import com.ykcoding.recipefinderapp.extensions.zipSuspendable
import com.ykcoding.recipefinderapp.helper.EventHandler
import com.ykcoding.recipefinderapp.helper.NetworkResponse
import com.ykcoding.recipefinderapp.presentation.view_state.HomeScreenUIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeScreenViewModel(
    private val recipesUseCase: RecipesUseCase,
    private val randomRecipesUseCase: RandomRecipesUseCase
): ViewModel() {

    private val _homeScreenUiState = MutableStateFlow(HomeScreenUIState())
    val homeScreenUiState = _homeScreenUiState.asStateFlow()

    init {
        viewModelScope.launch {
            showContent()
        }
    }

    private suspend fun showContent() {
        with(zipSuspendable(
            { recipesUseCase(sort = "popularity", maxReadyTime = null) },
            { recipesUseCase(sort = "healthiness", maxReadyTime = null) },
            { recipesUseCase(sort = null, maxReadyTime = 30) },
            { randomRecipesUseCase.invoke() }
        )) {
            setHomeScreenContent(
                first = first,
                second = second,
                third = third,
                fourth = fourth
            )
        }
    }

    private fun setHomeScreenContent(
        first: Flow<NetworkResponse<Recipes>>,
        second: Flow<NetworkResponse<Recipes>>,
        third: Flow<NetworkResponse<Recipes>>,
        fourth: Flow<NetworkResponse<RandomRecipes>>
    ) {

        collectRecipes(
            flow = first,
            mapData = { it.results },
            updateState = { state, result ->
                state.copy(popularRecipes = result)
            }
        )
        collectRecipes(
            flow = second,
            mapData = { it.results },
            updateState = { state, result ->
                state.copy(quickRecipes = result)
            }
        )
        collectRecipes(
            flow = third,
            mapData = { it.results },
            updateState = { state, result ->
                state.copy(healthyRecipes = result)
            }
        )
        collectRecipes(
            flow = fourth,
            mapData = { it.results },
            updateState = { state, result ->
                state.copy(randomRecipes = result)
            }
        )
    }

    private fun <T : Any> collectRecipes(
        flow: Flow<NetworkResponse<T>>,
        mapData: (T) -> List<Result>,
        updateState: (HomeScreenUIState, HomeScreenUIState.SectionState) -> HomeScreenUIState
    ) {
        flow
            .onStart {
                _homeScreenUiState.update {
                    updateState(it, HomeScreenUIState.SectionState.IsLoading)
                }
            }
            .onEach { response ->
                val result = when(response) {
                    is NetworkResponse.Success -> HomeScreenUIState.SectionState.Success(mapData(response.body))
                    is NetworkResponse.Error -> HomeScreenUIState.SectionState.Error(EventHandler(response))
                }
                _homeScreenUiState.update { uiState ->
                    updateState(uiState, result)
                }

            }.launchIn(viewModelScope)
    }

}