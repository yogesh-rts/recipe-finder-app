package com.ykcoding.recipefinderapp.domain.model

import com.ykcoding.recipefinderapp.data.remote.dto.RecipesDto

data class Recipes(
    val number: Int,
    val offset: Int,
    val results: List<RecipesDto.Result>,
    val totalResults: Int
)