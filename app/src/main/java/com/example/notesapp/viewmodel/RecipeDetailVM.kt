package com.example.notesapp.viewmodel

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import androidx.lifecycle.MutableLiveData
import com.example.notesapp.db.AppDatabase
import com.example.notesapp.entity.Rating
import com.example.notesapp.entity.Recipe
import com.example.notesapp.model.RecipeDataManager
import com.example.notesapp.skeleton.mvvm.BaseViewModel
import io.reactivex.functions.Consumer
import javax.inject.Inject

/**
 * Created by Richard Gross on 2020-01-13
 */
class RecipeDetailVM @Inject constructor(
    private val dataManager: RecipeDataManager,
    private val appDatabase: AppDatabase
) : BaseViewModel() {

    var recipeId: String? = null
    val name: MutableLiveData<String> = MutableLiveData()
    val description: MutableLiveData<String> = MutableLiveData()
    val duration: MutableLiveData<Int> = MutableLiveData()
    val ingredients: ObservableList<String> = ObservableArrayList()
    val info: MutableLiveData<String> = MutableLiveData()
    val score: MutableLiveData<Float> = MutableLiveData()

    val newRating: MutableLiveData<Float> = MutableLiveData()
    val ratingAvailable: MutableLiveData<Boolean> = MutableLiveData()

    init {
        newRating.observeForever(this::rateApp)
        ratingAvailable.value = true
    }

    fun loadRecipeDetail(id: String) {
        loading.value = true
        val rating = appDatabase.ratingDao().findRecipe(id)
        ratingAvailable.value = rating != null
//        newRating.value = rating?.score
        subscribeSingle(dataManager.loadRecipeDetail(id), Consumer(this::onRecipeDetailLoaded))
    }

    private fun onRecipeDetailLoaded(recipe: Recipe) {
        loading.value = false
        recipeId = recipe.id
        ingredients.isNullOrEmpty()
        name.value = recipe.name
        description.value = recipe.description
        duration.value = recipe.duration
        ingredients.apply {
            clear()
            recipe.ingredients?.let { it1 -> addAll(it1) }
        }
        info.value = recipe.info
        score.value = recipe.score
    }

    private fun rateApp(newRating: Float) {
        if (ratingAvailable.value!!) {
            recipeId?.let {
                subscribeSingle(
                    dataManager.addRating(it, newRating.toInt()),
                    Consumer(this::onAppRated)
                )
            }
        }
    }

    private fun onAppRated(rating: Rating) {
        // todo some code which store which id has been rated to avoid multiple rating of some recipe
    }
}