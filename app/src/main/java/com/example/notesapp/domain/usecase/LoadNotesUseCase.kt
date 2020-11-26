package com.example.notesapp.domain.usecase

import com.example.notesapp.presentation.entity.NoteFace
import io.reactivex.Single

/**
 * @author richardgross on 26/11/2020
 */
interface LoadNotesUseCase {
    fun loadNotes(): Single<List<NoteFace>>
}