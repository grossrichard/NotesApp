package com.example.notesapp.api

import android.util.Log
import com.example.notesapp.api.dto.RatingDto
import com.example.notesapp.api.dto.RecipeDto
import io.reactivex.Single
import java.util.concurrent.TimeUnit

/**
 * Created by Richard Gross on 2020-01-19
 */
class RecipeApiDefinitionMock : RecipeApiDefinition {

    companion object {
        private const val MOCK_RESPONSE_DURATION_MILLIS = 2000L
    }

    override fun loadRecipes(limit: Int, offset: Int): Single<List<RecipeDto>> {
        Log.d("TAG", "RecipeApiDefinitionMock loadRecipes()")
        return Single.just(MockDataProvider.createMockLoadRecipesResp())
            .delay(MOCK_RESPONSE_DURATION_MILLIS, TimeUnit.MILLISECONDS)
    }

    override fun addRecipe(recipe: RecipeDto): Single<RecipeDto> {
        return Single.just(recipe)
            .delay(MOCK_RESPONSE_DURATION_MILLIS, TimeUnit.MILLISECONDS)
    }

    override fun updateRecipe(id: String, recipe: RecipeDto): Single<RecipeDto> {
        return Single.just(recipe)
            .delay(MOCK_RESPONSE_DURATION_MILLIS, TimeUnit.MILLISECONDS)
    }

    override fun deleteRecipe(id: String) {
        // todo implement
    }

    override fun loadRecipeDetail(id: String): Single<RecipeDto> =
        Single.just(MockDataProvider.createLoadRecipeDetailResp())
            .delay(MOCK_RESPONSE_DURATION_MILLIS, TimeUnit.MILLISECONDS)

    override fun addRating(id: String, score: Int): Single<RatingDto> {
        return Single.just(MockDataProvider.createMockAddRatingResp())
            .delay(MOCK_RESPONSE_DURATION_MILLIS, TimeUnit.MILLISECONDS)
    }
}