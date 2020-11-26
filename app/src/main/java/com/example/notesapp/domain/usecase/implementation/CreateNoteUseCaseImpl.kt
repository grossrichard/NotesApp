package com.example.notesapp.domain.usecase.implementation

import com.example.notesapp.domain.repository.NotesRepository
import com.example.notesapp.domain.usecase.CreateNoteUseCase
import com.example.notesapp.entity.Note
import com.example.notesapp.infrastructure.api.dto.NoteDto
import io.reactivex.Single

class CreateNoteUseCaseImpl(private val repository: NotesRepository) : CreateNoteUseCase {

    override fun createNote(note: NoteDto): Single<Note> = repository.createNote(note)
}