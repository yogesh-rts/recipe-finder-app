package com.ykcoding.recipefinderapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ykcoding.recipefinderapp.domain.usecases.RecipesUseCase
import com.ykcoding.recipefinderapp.helper.NetworkResponse
import com.ykcoding.recipefinderapp.presentation.view_state.RecipeListState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.core.qualifier._q

class RecipesViewModel(
    private val recipesUseCase: RecipesUseCase
): ViewModel() {

    private val _state = MutableStateFlow(RecipeListState())
    val state = _state.asStateFlow()

    fun getRecipes(query: String, cuisine: String?) {
        _state.value = RecipeListState(isLoading = true)
        recipesUseCase(query, cuisine).onEach { result ->
            when(result) {
                is NetworkResponse.Success -> {
                    _state.value = RecipeListState(isLoading = false, result = result.body)
                }
                is NetworkResponse.Error -> {
                    _state.value = RecipeListState(isLoading = false, error = result.handleErrorMessage())
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
        "Korean ",
        "Japanese",
        "Mexican"
    )


}