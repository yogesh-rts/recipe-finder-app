package com.ykcoding.recipefinderapp.domain.model

import com.ykcoding.recipefinderapp.data.remote.dto.RecipesDto.Result

data class Recipes (
    val number: Int,
    val offset: Int,
    val results: List<Result>,
    val totalResults: Int
) {
    data class Result(
        val id: Int,
        val image: String,
        val imageType: String,
        val title: String
    )
}