package com.example.notesapp.domain.usecase

import com.example.notesapp.data.entity.EmptyDto
import io.reactivex.Single

/**
 * @author richardgross on 26/11/2020
 */
interface DeleteNoteUseCase {
    fun deleteNote(id: Long): Single<EmptyDto>
}