package com.ykcoding.recipefinderapp.domain.usecases

import com.ykcoding.recipefinderapp.data.remote.dto.toRecipes
import com.ykcoding.recipefinderapp.domain.model.Recipes
import com.ykcoding.recipefinderapp.domain.repo.RecipesRepo
import com.ykcoding.recipefinderapp.helper.NetworkResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class RecipesUseCase(val recipesRepo: RecipesRepo) {

    operator fun invoke(query: String?, cuisine: String?): Flow<NetworkResponse<Recipes>> = flow {
        try {
            val response = recipesRepo.fetchRecipes(query = query, cuisine = cuisine).toRecipes()
            emit(NetworkResponse.Success(response))
        } catch (e: HttpException) {
            emit(NetworkResponse.Error.Api(code = e.code(), message = e.message()))
        } catch (e: IOException) {
            emit(NetworkResponse.Error.Network(error = e))
        } catch (e: Throwable) {
            emit(NetworkResponse.Error.Unknown(error = e))
        }
    }
}