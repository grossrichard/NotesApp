package com.example.notesapp.model

import com.example.notesapp.api.Converter
import com.example.notesapp.api.RecipeApiService
import com.example.notesapp.db.AppDatabase
import com.example.notesapp.entity.Rating
import com.example.notesapp.entity.Recipe
import com.example.notesapp.skeleton.mvvm.BaseDataManager
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Richard Gross on 2020-01-18
 */

@Singleton
class RecipeDataManager @Inject constructor(
    private val apiService: RecipeApiService,
    private val db: AppDatabase
) :
    BaseDataManager() {

    fun loadRecipes(): Single<List<Recipe>> =
        apiService.service.loadRecipes(10, 1)
            .map { Converter.convert(it) }
            .doOnSuccess { db.recipeDao().addAll(it) }
            .onErrorResumeNext { Single.just(db.recipeDao().getAll()) }

    fun addRecipe(recipe: Recipe): Single<Recipe> =
        apiService.service.addRecipe(Converter.convert(recipe)).map { Converter.convert(it) }

    fun loadRecipeDetail(id: String): Single<Recipe> =
        apiService.service.loadRecipeDetail(id)
            .map { Converter.convert(it) }
            .doOnSuccess { db.recipeDetailDao().addRecipeDetail(it) }
            .onErrorResumeNext { Single.just(db.recipeDetailDao().getRecipeDetail(id)) }

    fun addRating(id: String, score: Int): Single<Rating> =
        apiService.service.addRating(id, score).map { Converter.convert(it) }
            .doOnSuccess { db.ratingDao().addRating(it) }

    //todo how to implement?
    fun updateRecipe(id: String, recipe: Recipe): Single<Recipe> =
        apiService.service.updateRecipe(id, Converter.convert(recipe)).map { Converter.convert(it) }

    //todo how to implement?
    fun deleteRecipe(id: String) = apiService.service.deleteRecipe(id)
}