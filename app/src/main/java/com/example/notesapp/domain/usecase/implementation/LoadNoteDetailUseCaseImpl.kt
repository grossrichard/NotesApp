package com.example.notesapp.domain.usecase.implementation

import com.example.notesapp.domain.repository.NotesRepository
import com.example.notesapp.domain.usecase.LoadNoteDetailUseCase
import com.example.notesapp.entity.Note
import io.reactivex.Single

class LoadNoteDetailUseCaseImpl(private val repository: NotesRepository) : LoadNoteDetailUseCase {
    override fun loadNoteDetail(id: String): Single<Note> = repository.loadNoteDetail(id)
}