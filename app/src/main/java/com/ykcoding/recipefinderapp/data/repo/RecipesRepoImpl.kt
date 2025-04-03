package com.ykcoding.recipefinderapp.data.repo

import com.ykcoding.recipefinderapp.data.remote.dto.RecipesDto
import com.ykcoding.recipefinderapp.data.service.recipes.Recipes
import com.ykcoding.recipefinderapp.domain.repo.RecipesRepo

class RecipesRepoImpl(private val service: Recipes): RecipesRepo {

    override suspend fun fetchRecipes(query: String?, cuisine: String?): RecipesDto {
        return service.fetchRecipes(query = query, cuisine = cuisine)
    }

}