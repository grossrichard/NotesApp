package com.example.notesapp.api

import com.example.notesapp.api.dto.NoteDto
import com.example.notesapp.entity.Note

/**
 * Created by Richard Gross on 2020-01-19
 */

object Converter {

    fun convert(recipesDtoList: List<NoteDto>): List<Note> = recipesDtoList.map { convert(it) }

    fun convert(noteDto: NoteDto): Note =
        Note(
            id = noteDto.id ?: "",
            title = noteDto.title
        )

    fun convert(note: Note): NoteDto =
        NoteDto(
            id = if (note.id.isBlank()) null else note.id,
            title = note.title
        )
}