package com.example.notesapp.domain.usecase

import com.example.notesapp.entity.Note
import com.example.notesapp.infrastructure.api.dto.NoteDto
import io.reactivex.Single

/**
 * @author richardgross on 26/11/2020
 */
interface CreateNoteUseCase {
    fun createNote(note: NoteDto): Single<Note>
}