package com.example.notesapp.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.notesapp.R
import com.example.notesapp.entity.Note
import com.example.notesapp.entity.NoteDetailMode
import com.example.notesapp.entity.NoteDetailMode.*
import com.example.notesapp.domain.NotesDataManager
import com.example.notesapp.skeleton.mvvm.BaseViewModel
import com.example.notesapp.skeleton.mvvm.event.MessageEvent
import io.reactivex.functions.Consumer
import javax.inject.Inject

/**
 * Created by Richard Gross on 2020-01-13
 */
class NoteDetailVM @Inject constructor(private val dataManager: NotesDataManager) :
    BaseViewModel() {

    var id: String? = null
    val mode: MutableLiveData<NoteDetailMode> = MutableLiveData()
    val title: MutableLiveData<String> = MutableLiveData()

    override fun loadData() {
        super.loadData()

        when (mode.value) {
            CREATE -> loading.value = false
            EDIT, READ, DELETE -> id?.let {
                subscribeSingle(
                    dataManager.loadNoteDetail(it),
                    Consumer(this::onNoteDetailLoaded)
                )
            }
        }
    }

    fun deleteNote() {
        id?.let { subscribeSingle(dataManager.deleteNote(it), Consumer { onResult() }) }
    }

    fun createNote() {
        subscribeSingle(dataManager.createNote(Note("", title.value)), Consumer { onResult() })
    }

    fun editNote() {
        id?.let {
            subscribeSingle(
                dataManager.updateNote(it, Note(it, title.value)),
                Consumer { onResult() })
        }
    }

    private fun onResult() {
        when (mode.value) {
            EDIT -> publish(MessageEvent(R.string.dialog_note_edited))
            CREATE -> publish(MessageEvent(R.string.dialog_note_created))
            DELETE -> publish(MessageEvent(R.string.dialog_note_deleted))
        }
    }

    private fun onNoteDetailLoaded(note: Note) {
        loading.value = false
        id = note.id
        title.value = note.title
    }
}