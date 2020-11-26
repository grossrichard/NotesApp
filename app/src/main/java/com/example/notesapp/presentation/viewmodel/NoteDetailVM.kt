package com.example.notesapp.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.notesapp.R
import com.example.notesapp.presentation.entity.NoteFace
import com.example.notesapp.presentation.entity.NoteDetailMode
import com.example.notesapp.presentation.entity.NoteDetailMode.*
import com.example.notesapp.domain.repository.NotesDataManager
import com.example.notesapp.domain.usecase.CreateNoteUseCase
import com.example.notesapp.domain.usecase.DeleteNoteUseCase
import com.example.notesapp.domain.usecase.LoadNoteDetailUseCase
import com.example.notesapp.domain.usecase.UpdateNoteUseCase
import com.example.notesapp.skeleton.mvvm.BaseViewModel
import com.example.notesapp.skeleton.mvvm.event.MessageEvent
import io.reactivex.functions.Consumer

/**
 * Created by Richard Gross on 2020-01-13
 */
class NoteDetailVM(
    private val loadNoteDetailUseCase: LoadNoteDetailUseCase,
    private val createNoteUseCase: CreateNoteUseCase,
    private val updateNoteUseCase: UpdateNoteUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase
) : BaseViewModel() {

    var id: Long? = null
    val mode: MutableLiveData<NoteDetailMode> = MutableLiveData()
    val title: MutableLiveData<String> = MutableLiveData()

    override fun loadData() {
        super.loadData()

        when (mode.value) {
            CREATE -> loading.value = false
            EDIT, READ, DELETE -> id?.let {
                subscribeSingle(
                    loadNoteDetailUseCase.loadNoteDetail(it),
                    Consumer(this::onNoteDetailLoaded)
                )
            }
        }
    }

    fun deleteNote() = id?.let {
        subscribeSingle(deleteNoteUseCase.deleteNote(it), Consumer { onResult() })
    }

    fun createNote() = subscribeSingle(
        createNoteUseCase.createNote(NoteFace(id, title.value)), Consumer { onResult() })

    fun editNote() = id?.let {
        subscribeSingle(
            updateNoteUseCase.updateNote(it, NoteFace(it, title.value)),
            Consumer { onResult() })
    }

    private fun onResult() {
        when (mode.value) {
            EDIT -> publish(MessageEvent(R.string.dialog_note_edited))
            CREATE -> publish(MessageEvent(R.string.dialog_note_created))
            DELETE -> publish(MessageEvent(R.string.dialog_note_deleted))
        }
    }

    private fun onNoteDetailLoaded(note: NoteFace) {
        loading.value = false
        id = note.id
        title.value = note.title
    }
}