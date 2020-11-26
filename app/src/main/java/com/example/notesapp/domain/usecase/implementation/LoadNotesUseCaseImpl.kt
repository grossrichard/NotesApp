package com.example.notesapp.domain.usecase.implementation

import com.example.notesapp.domain.repository.NotesRepository
import com.example.notesapp.domain.usecase.LoadNotesUseCase
import com.example.notesapp.entity.Note
import io.reactivex.Single

class LoadNotesUseCaseImpl(private val repository: NotesRepository) : LoadNotesUseCase {
    override fun loadNotes(): Single<List<Note>> = repository.loadNotes()
}