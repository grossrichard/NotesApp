package com.example.notesapp.presentation.viewmodel

import androidx.databinding.ObservableArrayList
import com.example.notesapp.entity.Note
import com.example.notesapp.entity.NoteDetailMode
import com.example.notesapp.domain.NotesDataManager
import com.example.notesapp.presentation.fragment.NotesListFragmentDirections
import com.example.notesapp.skeleton.mvvm.BaseViewModel
import com.example.notesapp.skeleton.mvvm.event.NavigateEvent
import io.reactivex.functions.Consumer

/**
 * Created by Richard Gross on 2020-01-13
 */
class NotesListVM(private var loadNotesUseCase: NotesDataManager) : BaseViewModel() {

    var notesList = ObservableArrayList<Note>()


    fun loadNotes() {
        loading.value = true
        subscribeSingle(loadNotesUseCase.loadNotes(), Consumer(this::onNotesLoaded))
    }

    private fun onNotesLoaded(list: List<Note>) {
        loading.value = false
        notesList.clear()
        notesList.addAll(list)
    }

    fun onItemClicked(note: Note) {
        publish(
            NavigateEvent(
                NotesListFragmentDirections.navigateNotesListToNoteDetail(
                    note.id,
                    NoteDetailMode.READ
                )
            )
        )
    }
}

