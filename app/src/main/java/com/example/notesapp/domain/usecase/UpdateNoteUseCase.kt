package com.example.notesapp.domain.usecase

import com.example.notesapp.entity.Note
import com.example.notesapp.infrastructure.api.dto.NoteDto
import io.reactivex.Single

/**
 * @author richardgross on 26/11/2020
 */
interface UpdateNoteUseCase {
    fun updateNote(id: String, note: NoteDto): Single<Note>
}