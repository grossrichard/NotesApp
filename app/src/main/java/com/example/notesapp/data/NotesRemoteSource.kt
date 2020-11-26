package com.example.notesapp.data

import com.example.notesapp.data.entity.EmptyDto
import com.example.notesapp.data.entity.NoteDto
import com.example.notesapp.presentation.entity.NoteFace
import io.reactivex.Single

/**
 * @author richardgross on 26/11/2020
 */
interface NotesRemoteSource {
    fun loadNotes(): Single<List<NoteDto>>
    fun loadNoteDetail(id: Long): Single<NoteDto>
    fun createNote(note: NoteDto): Single<NoteDto>
    fun updateNote(id: Long, note: NoteDto): Single<NoteDto>
    fun deleteNote(id: Long): Single<EmptyDto>
}