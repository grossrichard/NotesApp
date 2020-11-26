package com.example.notesapp.domain.usecase

import com.example.notesapp.infrastructure.api.dto.EmptyDto
import io.reactivex.Single
import retrofit2.Response

/**
 * @author richardgross on 26/11/2020
 */
interface DeleteNoteUseCase {
    fun deleteNote(id: String): Single<EmptyDto>
}