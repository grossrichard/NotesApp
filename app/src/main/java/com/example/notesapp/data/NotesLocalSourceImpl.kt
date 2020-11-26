package com.example.notesapp.data

import com.example.notesapp.data.entity.EmptyDto
import com.example.notesapp.data.entity.NoteDbo
import com.example.notesapp.data.entity.NoteDto
import com.example.notesapp.infrastructure.api.Mapper
import com.example.notesapp.infrastructure.db.AppDatabase
import com.example.notesapp.presentation.entity.NoteFace
import io.reactivex.Single

class NotesLocalSourceImpl(private val db: AppDatabase) : NotesLocalSource {

    override fun loadNotes(): Single<List<NoteDbo>> = db.noteDao().getAll()

    override fun loadNoteDetail(id: Long): Single<NoteDbo> = db.noteDao().findById(id)

    override fun createNote(note: NoteDbo): Single<NoteDbo> = db.noteDao()
        .insert(note)
        .let { Single.just(note) }

    override fun updateNote(id: Long, note: NoteDto): Single<NoteDbo> = db.noteDao().findById(id)

    override fun deleteNote(id: Long): Single<EmptyDto> = db.noteDao()
        .delete(id)
        .let { Single.just(EmptyDto()) }
}