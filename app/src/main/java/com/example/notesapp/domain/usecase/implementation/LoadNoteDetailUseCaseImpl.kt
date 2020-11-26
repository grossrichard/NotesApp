package com.example.notesapp.domain.usecase.implementation

import com.example.notesapp.domain.entity.Mapper.toNoteFace
import com.example.notesapp.domain.repository.NotesRepository
import com.example.notesapp.domain.usecase.LoadNoteDetailUseCase
import com.example.notesapp.presentation.entity.NoteFace
import io.reactivex.Single

class LoadNoteDetailUseCaseImpl(private val repo: NotesRepository) : LoadNoteDetailUseCase {

    override fun loadNoteDetail(id: Long): Single<NoteFace> = repo
        .loadNoteDetail(id)
        .map { it.toNoteFace() }
}