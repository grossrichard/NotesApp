package com.example.notesapp.domain.repository.implementation

import com.example.notesapp.data.NotesLocalSource
import com.example.notesapp.data.NotesRemoteSource
import com.example.notesapp.domain.entity.NoteBdo
import com.example.notesapp.domain.repository.NotesRepository
import com.example.notesapp.presentation.entity.NoteFace
import com.example.notesapp.data.entity.EmptyDto
import com.example.notesapp.data.entity.Mapper.toNoteBdo
import com.example.notesapp.data.entity.Mapper.toNoteDto
import com.example.notesapp.domain.entity.Mapper.toNoteBdo
import io.reactivex.Single

class NotesRepositoryImpl(
    val localSource: NotesLocalSource,
    private val remoteSource: NotesRemoteSource
) : NotesRepository {

    // TODO: 26/11/2020 implement correctly

    override fun loadNotes(): Single<List<NoteBdo>> = remoteSource
        .loadNotes()
        .map { it.map { dto -> dto.toNoteBdo() } }

    override fun loadNoteDetail(id: Long): Single<NoteBdo> = remoteSource
        .loadNoteDetail(id)
        .map { it.toNoteBdo() }

    override fun createNote(note: NoteBdo): Single<NoteBdo> = remoteSource
        .createNote(note.toNoteDto())
        .map { it.toNoteBdo() }

    override fun updateNote(id: Long, note: NoteBdo): Single<NoteBdo> = remoteSource
        .updateNote(id, note.toNoteDto())
        .map { it.toNoteBdo() }

    override fun deleteNote(id: Long): Single<EmptyDto> = deleteNote(id)
}