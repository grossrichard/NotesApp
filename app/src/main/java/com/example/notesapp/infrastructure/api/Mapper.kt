package com.example.notesapp.infrastructure.api

import com.example.notesapp.data.entity.NoteDto
import com.example.notesapp.presentation.entity.NoteFace

/**
 * Created by Richard Gross on 2020-01-19
 */

object Mapper {

    fun convert(recipesDtoList: List<NoteDto>): List<NoteFace> = recipesDtoList.map { convert(it) }

    fun convert(noteDto: NoteDto): NoteFace =
        NoteFace(
            id = noteDto.id,
            title = noteDto.title
        )

    fun convert(note: NoteFace): NoteDto =
        NoteDto(
            id = note.id,
            title = note.title
        )
}