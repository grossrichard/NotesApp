package com.example.notesapp.domain.repository

import com.example.notesapp.domain.entity.NoteBdo
import com.example.notesapp.data.entity.EmptyDto
import io.reactivex.Single

/**
 * @author richardgross on 26/11/2020
 */
interface NotesRepository {
    fun loadNotes(): Single<List<NoteBdo>>
    fun loadNoteDetail(id: Long): Single<NoteBdo>
    fun createNote(note: NoteBdo): Single<NoteBdo>
    fun updateNote(id: Long, note: NoteBdo): Single<NoteBdo>
    fun deleteNote(id: Long): Single<EmptyDto>
}