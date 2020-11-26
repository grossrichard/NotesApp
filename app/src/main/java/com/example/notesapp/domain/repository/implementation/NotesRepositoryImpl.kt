package com.example.notesapp.domain.repository.implementation

import com.example.notesapp.data.NotesLocalSource
import com.example.notesapp.data.NotesRemoteSource
import com.example.notesapp.domain.repository.NotesRepository
import com.example.notesapp.entity.Note
import com.example.notesapp.infrastructure.api.dto.EmptyDto
import com.example.notesapp.infrastructure.api.dto.NoteDto
import io.reactivex.Single
import retrofit2.Response

class NotesRepositoryImpl(
    val localSource: NotesLocalSource,
    private val remoteSource: NotesRemoteSource
) : NotesRepository {

    // TODO: 26/11/2020 implement correctly

    override fun loadNotes(): Single<List<Note>> = remoteSource.loadNotes()

    override fun loadNoteDetail(id: String): Single<Note> = remoteSource.loadNoteDetail(id)

    override fun createNote(note: NoteDto): Single<Note> = remoteSource.createNote(note)

    override fun updateNote(id: String, note: NoteDto): Single<Note> =
        remoteSource.updateNote(id, note)

    override fun deleteNote(id: String): Single<EmptyDto> = deleteNote(id)
}