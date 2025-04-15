package com.ykcoding.recipefinderapp.data.remote.dto

import com.squareup.moshi.JsonClass
import com.ykcoding.recipefinderapp.domain.model.RandomRecipes

@JsonClass(generateAdapter = true)
data class RandomRecipesDto(
    val recipes: List<Result>
)

fun RandomRecipesDto.toRandomRecipes(): RandomRecipes {
    return RandomRecipes(results = recipes)
}