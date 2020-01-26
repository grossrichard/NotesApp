package com.example.notesapp.viewmodel

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import androidx.lifecycle.MutableLiveData
import com.example.notesapp.entity.Note
import com.example.notesapp.model.NotesDataManager
import com.example.notesapp.skeleton.mvvm.BaseViewModel
import com.example.notesapp.skeleton.mvvm.event.RecipeSuccessfullyAddedEvent
import io.reactivex.functions.Consumer
import javax.inject.Inject

/**
 * Created by Richard Gross on 2020-01-13
 */
class AddRecipeVM @Inject constructor(private var dataManager: NotesDataManager) :
    BaseViewModel() {

    val title: MutableLiveData<String> = MutableLiveData()

    fun addRecipe() {
        loading.value = true
        subscribeSingle(
            dataManager.addNote(
                Note(
                    id = "",
                    title = title.value
                )
            ), Consumer(this::onRecipeAdded)
        )
    }

    private fun onRecipeAdded(note: Note) {
        loading.value = false
        publish(RecipeSuccessfullyAddedEvent())
    }

    fun onIngredientAdded() {
//        if (!newIngredient.value.isNullOrBlank() || ingredients.any { it == newIngredient.value }) {
//            ingredients.add(newIngredient.value)
//            newIngredient.value = null
//        }
    }
}