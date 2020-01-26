package com.example.notesapp.viewmodel

import androidx.databinding.ObservableArrayList
import com.example.notesapp.entity.Recipe
import com.example.notesapp.model.RecipeDataManager
import com.example.notesapp.skeleton.mvvm.BaseViewModel
import com.example.notesapp.skeleton.mvvm.event.NavigateEvent
import com.example.notesapp.view.RecipeListFragmentDirections
import io.reactivex.functions.Consumer
import javax.inject.Inject

/**
 * Created by Richard Gross on 2020-01-13
 */
class RecipeListVM @Inject constructor(private var dataManager: RecipeDataManager) :
    BaseViewModel() {

    var recipesList = ObservableArrayList<Recipe>()

    fun loadRecipes() {
        loading.value = true
        subscribeSingle(dataManager.loadRecipes(), Consumer(this::onRecipesLoaded))
    }

    private fun onRecipesLoaded(list: List<Recipe>) {
        loading.value = false
        recipesList.clear()
        recipesList.addAll(list)
    }

    fun onItemClicked(recipe: Recipe) {
        publish(NavigateEvent(RecipeListFragmentDirections.navigateRecipeListToRecipeDetail(recipe.id)))
    }
}

