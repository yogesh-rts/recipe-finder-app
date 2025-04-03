package com.ykcoding.recipefinderapp.data.service.recipes

import com.ykcoding.recipefinderapp.data.EndPoint
import com.ykcoding.recipefinderapp.data.Param
import com.ykcoding.recipefinderapp.data.remote.dto.RecipesDto
import com.ykcoding.recipefinderapp.helper.NetworkResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface Recipes {

    @GET(EndPoint.RECIPES + "/" + EndPoint.COMPLEX_SEARCH)
    suspend fun fetchRecipes(
        @Query(Param.QUERY) query: String?,
        @Query(Param.CUISINE) cuisine: String?
    ): NetworkResponse<RecipesDto>
}
