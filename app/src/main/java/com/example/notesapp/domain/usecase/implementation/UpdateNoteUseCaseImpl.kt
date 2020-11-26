package com.example.notesapp.domain.usecase.implementation

import com.example.notesapp.domain.entity.Mapper.toNoteBdo
import com.example.notesapp.domain.entity.Mapper.toNoteFace
import com.example.notesapp.domain.repository.NotesRepository
import com.example.notesapp.domain.usecase.UpdateNoteUseCase
import com.example.notesapp.presentation.entity.NoteFace
import io.reactivex.Single

class UpdateNoteUseCaseImpl(private val repo: NotesRepository) : UpdateNoteUseCase {

    override fun updateNote(id: Long, note: NoteFace): Single<NoteFace> = repo
        .updateNote(id, note.toNoteBdo())
        .map { it.toNoteFace() }
}