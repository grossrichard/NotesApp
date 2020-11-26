package com.example.notesapp.domain.usecase.implementation

import com.example.notesapp.domain.entity.Mapper.toNoteFace
import com.example.notesapp.domain.repository.NotesRepository
import com.example.notesapp.domain.usecase.LoadNotesUseCase
import com.example.notesapp.presentation.entity.NoteFace
import io.reactivex.Single

class LoadNotesUseCaseImpl(private val repo: NotesRepository) : LoadNotesUseCase {

    override fun loadNotes(): Single<List<NoteFace>> = repo
        .loadNotes()
        .map { it.map { bdo -> bdo.toNoteFace() } }
}