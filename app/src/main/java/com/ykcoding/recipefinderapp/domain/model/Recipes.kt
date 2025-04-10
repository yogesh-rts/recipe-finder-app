package com.ykcoding.recipefinderapp.domain.model

import com.ykcoding.recipefinderapp.data.remote.dto.Result

data class Recipes(
    val number: Int,
    val offset: Int,
    val results: List<Result>,
    val totalResults: Int
)