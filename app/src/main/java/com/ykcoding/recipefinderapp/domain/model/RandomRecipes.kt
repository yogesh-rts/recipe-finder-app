package com.ykcoding.recipefinderapp.domain.model

import com.ykcoding.recipefinderapp.data.remote.dto.Result

data class RandomRecipes(
    val recipes: List<Result>
)
