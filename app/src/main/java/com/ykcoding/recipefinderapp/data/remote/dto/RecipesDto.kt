package com.ykcoding.recipefinderapp.data.remote.dto

import com.squareup.moshi.JsonClass
import com.ykcoding.recipefinderapp.domain.model.Recipes

@JsonClass(generateAdapter = true)
data class RecipesDto(
    val number: Int,
    val offset: Int,
    val results: List<Result>,
    val totalResults: Int
) {
    @JsonClass(generateAdapter = true)
    data class Result(
        val id: Int,
        val image: String,
        val imageType: String,
        val title: String
    )
}

fun RecipesDto.toRecipes(): Recipes {
    return Recipes(
        number = number,
        offset = offset,
        results = results,
        totalResults = totalResults
    )
}