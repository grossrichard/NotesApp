package com.example.notesapp.domain.usecase.implementation

import com.example.notesapp.domain.repository.NotesRepository
import com.example.notesapp.domain.usecase.UpdateNoteUseCase
import com.example.notesapp.entity.Note
import com.example.notesapp.infrastructure.api.dto.NoteDto
import io.reactivex.Single

class UpdateNoteUseCaseImpl(private val repository: NotesRepository) : UpdateNoteUseCase {
    override fun updateNote(id: String, note: NoteDto): Single<Note> =
        repository.updateNote(id, note)
}