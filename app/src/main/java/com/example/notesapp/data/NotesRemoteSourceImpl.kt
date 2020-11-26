package com.example.notesapp.data

import com.example.notesapp.data.entity.EmptyDto
import com.example.notesapp.data.entity.NoteDto
import com.example.notesapp.infrastructure.api.NotesApiDefinition
import io.reactivex.Single

class NotesRemoteSourceImpl(private val api: NotesApiDefinition) : NotesRemoteSource {

    override fun loadNotes(): Single<List<NoteDto>> = api.loadNotes()

    override fun loadNoteDetail(id: Long): Single<NoteDto> = api.loadNoteDetail(id)

    override fun createNote(note: NoteDto): Single<NoteDto> = api.createNote(note)

    override fun updateNote(id: Long, note: NoteDto): Single<NoteDto> = api.updateNote(id, note)

    override fun deleteNote(id: Long): Single<EmptyDto> = api.deleteNote(id).map { EmptyDto() }
}