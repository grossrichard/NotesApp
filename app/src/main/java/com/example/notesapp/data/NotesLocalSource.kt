package com.example.notesapp.data

import com.example.notesapp.data.entity.EmptyDto
import com.example.notesapp.data.entity.NoteDbo
import com.example.notesapp.data.entity.NoteDto
import io.reactivex.Single

/**
 * @author richardgross on 26/11/2020
 */
interface NotesLocalSource {
    fun loadNotes(): Single<List<NoteDbo>>
    fun loadNoteDetail(id: Long): Single<NoteDbo>
    fun createNote(note: NoteDbo): Single<NoteDbo>
    fun updateNote(id: Long, note: NoteDto): Single<NoteDbo>
    fun deleteNote(id: Long): Single<EmptyDto>
}