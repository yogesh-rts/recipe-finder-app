package com.ykcoding.recipefinderapp.domain.usecases

import com.ykcoding.recipefinderapp.data.remote.dto.toRandomRecipes
import com.ykcoding.recipefinderapp.domain.model.RandomRecipes
import com.ykcoding.recipefinderapp.domain.repo.RecipesRepo
import com.ykcoding.recipefinderapp.helper.NetworkResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class RandomRecipesUseCase(private val randomRecipesRepo: RecipesRepo) {

    operator fun invoke(): Flow<NetworkResponse<RandomRecipes>> = flow {
        try {
            val response = randomRecipesRepo.showRandomRecipes().toRandomRecipes()
            emit(NetworkResponse.Success(response))
        }
        catch (e: HttpException) {
            emit(NetworkResponse.Error.Api(code = e.code(), message = e.message()))
        } catch (e: IOException) {
            emit(NetworkResponse.Error.Network(error = e))
        } catch (e: Throwable) {
            emit(NetworkResponse.Error.Unknown(error = e))
        }
    }
}