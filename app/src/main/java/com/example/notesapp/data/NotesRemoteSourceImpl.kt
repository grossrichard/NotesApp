package com.example.notesapp.data

import com.example.notesapp.domain.NotesDataManager
import com.example.notesapp.entity.Note
import com.example.notesapp.infrastructure.api.Converter
import com.example.notesapp.infrastructure.api.dto.EmptyDto
import com.example.notesapp.infrastructure.api.dto.NoteDto
import io.reactivex.Single

class NotesRemoteSourceImpl(private val dataManager: NotesDataManager) : NotesRemoteSource {
    override fun loadNotes(): Single<List<Note>> = dataManager.loadNotes()

    override fun loadNoteDetail(id: String): Single<Note> = dataManager.loadNoteDetail(id)

    override fun createNote(note: NoteDto): Single<Note> =
        dataManager.createNote(Converter.convert(note))

    override fun updateNote(id: String, note: NoteDto): Single<Note> =
        dataManager.updateNote(id, Converter.convert(note))

    override fun deleteNote(id: String): Single<EmptyDto> = dataManager.deleteNote(id)
}