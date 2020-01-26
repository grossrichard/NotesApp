package com.example.notesapp.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.notesapp.api.dto.EmptyDto
import com.example.notesapp.entity.Note
import com.example.notesapp.entity.NoteDetailMode
import com.example.notesapp.entity.NoteDetailMode.*
import com.example.notesapp.model.NotesDataManager
import com.example.notesapp.skeleton.mvvm.BaseViewModel
import com.example.notesapp.util.AlertDialogCreator
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
        id?.let { subscribeSingle(dataManager.deleteNote(it), Consumer(this::onNoteDeleted)) }
    }

    fun createNote() {
        id?.let {
            subscribeSingle(
                dataManager.createNote(Note(it, title.value)),
                Consumer(this::onNoteCreated)
            )
        }
    }

    fun editNote() {
        id?.let {
            subscribeSingle(
                dataManager.editNote(it, Note(it, title.value)),
                Consumer(this::onNoteEdited)
            )
        }
    }

    private fun onNoteDeleted(emptyDto: EmptyDto) {
    }

    private fun onNoteCreated(note: Note) {
        // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun onNoteEdited(note: Note) {
        // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun onNoteDetailLoaded(note: Note) {
        loading.value = false
        id = note.id
        title.value = note.title
    }
}