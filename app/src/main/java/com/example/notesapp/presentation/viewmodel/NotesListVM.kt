package com.example.notesapp.presentation.viewmodel

import androidx.databinding.ObservableArrayList
import com.example.notesapp.presentation.entity.NoteFace
import com.example.notesapp.presentation.entity.NoteDetailMode
import com.example.notesapp.domain.repository.NotesDataManager
import com.example.notesapp.domain.usecase.LoadNotesUseCase
import com.example.notesapp.presentation.fragment.NotesListFragmentDirections
import com.example.notesapp.skeleton.mvvm.BaseViewModel
import com.example.notesapp.skeleton.mvvm.event.NavigateEvent
import io.reactivex.functions.Consumer

/**
 * Created by Richard Gross on 2020-01-13
 */
class NotesListVM(private var loadNotesUseCase: LoadNotesUseCase) : BaseViewModel() {

    var notesList = ObservableArrayList<NoteFace>()

    fun loadNotes() {
        loading.value = true
        subscribeSingle(loadNotesUseCase.loadNotes(), Consumer(this::onNotesLoaded))
    }

    private fun onNotesLoaded(list: List<NoteFace>) {
        loading.value = false
        notesList.clear()
        notesList.addAll(list)
    }

    fun onItemClicked(note: NoteFace) = publish(
        NavigateEvent(
            NotesListFragmentDirections.navigateNotesListToNoteDetail(
                note.id as java.lang.Long,
                NoteDetailMode.READ
            )
        )
    )
}

