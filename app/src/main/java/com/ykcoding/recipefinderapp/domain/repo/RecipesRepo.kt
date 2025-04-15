package com.ykcoding.recipefinderapp.domain.repo

import com.ykcoding.recipefinderapp.data.remote.dto.RandomRecipesDto
import com.ykcoding.recipefinderapp.data.remote.dto.RecipesDto

interface RecipesRepo {

    suspend fun fetchRecipes(query: String?, cuisine: String?, category: String?): RecipesDto

    suspend fun showRecipes(sort: String?, maxReadyTime: Int?): RecipesDto

    suspend fun showRandomRecipes(): RandomRecipesDto

}