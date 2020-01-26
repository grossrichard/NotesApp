package com.example.notesapp.viewmodel

import androidx.databinding.ObservableArrayList
import com.example.notesapp.entity.Note
import com.example.notesapp.model.NotesDataManager
import com.example.notesapp.skeleton.mvvm.BaseViewModel
import io.reactivex.functions.Consumer
import javax.inject.Inject

/**
 * Created by Richard Gross on 2020-01-13
 */
class NotesListVM @Inject constructor(private var dataManager: NotesDataManager) :
    BaseViewModel() {

    var recipesList = ObservableArrayList<Note>()

    fun loadRecipes() {
        loading.value = true
        subscribeSingle(dataManager.loadNotes(), Consumer(this::onRecipesLoaded))
    }

    private fun onRecipesLoaded(list: List<Note>) {
        loading.value = false
        recipesList.clear()
        recipesList.addAll(list)
    }

    fun onItemClicked(note: Note) {
//        publish(NavigateEvent(RecipeListFragmentDirections.navigateRecipeListToRecipeDetail(note.id)))
    }
}

