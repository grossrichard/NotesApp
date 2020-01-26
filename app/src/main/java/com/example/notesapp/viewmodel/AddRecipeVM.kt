package com.example.notesapp.viewmodel

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import androidx.lifecycle.MutableLiveData
import com.example.notesapp.entity.Recipe
import com.example.notesapp.model.RecipeDataManager
import com.example.notesapp.skeleton.mvvm.BaseViewModel
import com.example.notesapp.skeleton.mvvm.event.RecipeSuccessfullyAddedEvent
import io.reactivex.functions.Consumer
import javax.inject.Inject

/**
 * Created by Richard Gross on 2020-01-13
 */
class AddRecipeVM @Inject constructor(private var dataManager: RecipeDataManager) :
    BaseViewModel() {

    val name: MutableLiveData<String> = MutableLiveData()
    val description: MutableLiveData<String> = MutableLiveData()
    val duration: MutableLiveData<String> = MutableLiveData()
    val ingredients: ObservableList<String> = ObservableArrayList()
    val info: MutableLiveData<String> = MutableLiveData()
    val newIngredient: MutableLiveData<String> = MutableLiveData()

    fun addRecipe() {
        loading.value = true
        subscribeSingle(
            dataManager.addRecipe(
                Recipe(
                    id = "",
                    name = this@AddRecipeVM.name.value,
                    description = this@AddRecipeVM.description.value,
                    info = this@AddRecipeVM.info.value,
                    duration = this@AddRecipeVM.duration.value?.toInt(),
                    ingredients = this@AddRecipeVM.ingredients
                )
            ), Consumer(this::onRecipeAdded)
        )
    }

    private fun onRecipeAdded(recipe: Recipe) {
        loading.value = false
        publish(RecipeSuccessfullyAddedEvent())
    }

    fun onIngredientAdded() {
        if (!newIngredient.value.isNullOrBlank() || ingredients.any { it == newIngredient.value }) {
            ingredients.add(newIngredient.value)
            newIngredient.value = null
        }
    }
}