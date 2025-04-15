package com.ykcoding.recipefinderapp.presentation.ui.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ykcoding.recipefinderapp.domain.usecases.RecipesUseCase
import com.ykcoding.recipefinderapp.helper.EventHandler
import com.ykcoding.recipefinderapp.helper.NetworkResponse
import com.ykcoding.recipefinderapp.presentation.view_state.FilterState
import com.ykcoding.recipefinderapp.presentation.view_state.SearchRecipeState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

class RecipesViewModel(private val recipesUseCase: RecipesUseCase): ViewModel() {

    private val _searchRecipeState = MutableStateFlow(SearchRecipeState())
    val searchRecipeListState = _searchRecipeState.asStateFlow()

    private val _filterState = MutableStateFlow(FilterState())
    val filterState = _filterState.asStateFlow()

    fun selectCuisine(cuisine: String) {
        _filterState.update {
            it.copy(selectedCuisine = cuisine)
        }
    }

    fun clearFilter() {
        _filterState.update {
            it.copy(selectedCuisine = null, selectedCategory = null)
        }
    }

    fun selectCategory(category: String) {
        _filterState.update {
            it.copy(selectedCategory = category)
        }
    }

    fun getRecipes(query: String, cuisine: String?, category: String?) {
        _searchRecipeState.value = SearchRecipeState(isLoading = true)
        recipesUseCase(query, cuisine, category).onEach { result ->
            when(result) {
                is NetworkResponse.Success -> {
                    _searchRecipeState.value = SearchRecipeState(
                        isLoading = false,
                        result = result.body
                    )
                }
                is NetworkResponse.Error -> {
                    _searchRecipeState.value = SearchRecipeState(
                        isLoading = false,
                        error = EventHandler(result)
                    )
                }
            }
        }.launchIn(viewModelScope)

        /* Or you can write in an alternative way as mentioned below

            viewModelScope.launch {
                recipesUseCase(query, cuisine).collect {
                    when(it) {
                        is NetworkResponse.Success -> {
                            // Collect and update the state here
                        }
                        is NetworkResponse.Error -> {
                            // Collect and update the error message here
                        }
                    }
                }
            }
         */
    }

    val categories = listOf(
        "Appetizer",
        "Breakfast",
        "Main course",
        "Snack",
        "Side dish",
        "Dessert",
        "Beverages",
    )

    val cuisine = listOf(
        "American",
        "Chinese",
        "European",
        "Mediterranean",
        "Indian",
        "Italian",
        "Korean",
        "Japanese",
        "Mexican"
    )


}