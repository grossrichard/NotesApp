package com.example.notesapp.domain.usecase.implementation

import com.example.notesapp.domain.entity.Mapper.toNoteBdo
import com.example.notesapp.domain.entity.Mapper.toNoteFace
import com.example.notesapp.domain.repository.NotesRepository
import com.example.notesapp.domain.usecase.CreateNoteUseCase
import com.example.notesapp.presentation.entity.NoteFace
import io.reactivex.Single

class CreateNoteUseCaseImpl(private val repo: NotesRepository) : CreateNoteUseCase {

    override fun createNote(note: NoteFace): Single<NoteFace> = repo
        .createNote(note.toNoteBdo())
        .map { it.toNoteFace() }
}