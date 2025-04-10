package com.ykcoding.recipefinderapp.data.service.recipes

import com.ykcoding.recipefinderapp.data.EndPoint
import com.ykcoding.recipefinderapp.data.Param
import com.ykcoding.recipefinderapp.data.remote.dto.RandomRecipesDto
import com.ykcoding.recipefinderapp.data.remote.dto.RecipesDto
import retrofit2.http.GET
import retrofit2.http.Query

interface Recipes {

    @GET(EndPoint.RECIPES + "/" + EndPoint.COMPLEX_SEARCH)
    suspend fun fetchRecipes(
        @Query(Param.QUERY) query: String?,
        @Query(Param.CUISINE) cuisine: String?,
        @Query(Param.CATEGORY) category: String?,
        @Query(Param.ADD_RECIPE_INFORMATION) addRecipeInformation: Boolean = true
    ): RecipesDto

    @GET(EndPoint.RECIPES + "/" + EndPoint.RANDOM_RECIPES)
    suspend fun showRandomRecipes(
        @Query(Param.INCLUDE_NUTRITION) includeNutrition: Boolean = true,
        @Query(Param.NUMBER) number: Int = 10
    ): RandomRecipesDto
}
