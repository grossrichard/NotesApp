package com.example.notesapp.domain.usecase.implementation

import com.example.notesapp.domain.repository.NotesRepository
import com.example.notesapp.domain.usecase.DeleteNoteUseCase
import com.example.notesapp.data.entity.EmptyDto
import io.reactivex.Single

class DeleteNoteUseCaseImpl(private val repo: NotesRepository) : DeleteNoteUseCase {
    override fun deleteNote(id: Long): Single<EmptyDto> = repo.deleteNote(id)
}