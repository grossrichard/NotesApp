package com.example.notesapp.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.notesapp.db.AppDatabase
import com.example.notesapp.entity.Note
import com.example.notesapp.model.NotesDataManager
import com.example.notesapp.skeleton.mvvm.BaseViewModel
import io.reactivex.functions.Consumer
import javax.inject.Inject

/**
 * Created by Richard Gross on 2020-01-13
 */
class NoteDetailVM @Inject constructor(
    private val dataManager: NotesDataManager,
    private val appDatabase: AppDatabase
) : BaseViewModel() {

    var id: String? = null
    val title = MutableLiveData<String>()

    fun loadNoteDetail(id: String) {
        loading.value = true
        subscribeSingle(dataManager.loadNoteDetail(id), Consumer(this::onNoteDetailLoaded))
    }

    private fun onNoteDetailLoaded(note: Note) {
        loading.value = false
        id = note.id
        title.value = note.title
    }
}