package com.example.notesapp.domain.repository

import com.example.notesapp.entity.Note
import com.example.notesapp.infrastructure.api.dto.EmptyDto
import com.example.notesapp.infrastructure.api.dto.NoteDto
import io.reactivex.Single

/**
 * @author richardgross on 26/11/2020
 */
interface NotesRepository {
    fun loadNotes(): Single<List<Note>>
    fun loadNoteDetail(id: String): Single<Note>
    fun createNote(note: NoteDto): Single<Note>
    fun updateNote(id: String, note: NoteDto): Single<Note>
    fun deleteNote(id: String): Single<EmptyDto>
}