package com.example.notesapp.domain.usecase.implementation

import com.example.notesapp.domain.repository.NotesRepository
import com.example.notesapp.domain.usecase.DeleteNoteUseCase
import com.example.notesapp.infrastructure.api.dto.EmptyDto
import io.reactivex.Single

class DeleteNoteUseCaseImpl(private val repository: NotesRepository) : DeleteNoteUseCase {
    override fun deleteNote(id: String): Single<EmptyDto> = repository.deleteNote(id)
}