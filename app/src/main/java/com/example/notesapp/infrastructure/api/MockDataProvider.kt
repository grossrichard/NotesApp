package com.example.notesapp.infrastructure.api

import com.example.notesapp.data.entity.NoteDto

/**
 * Created by Richard Gross on 2020-01-19
 */
object MockDataProvider {

    fun createMockLoadNotesResp(): List<NoteDto> = arrayListOf(
        NoteDto(1, "Jogging in park"),
        NoteDto(2, "Pick-up posters from post-office"),
        NoteDto(3, "Gym"),
        NoteDto(4, "Gym"),
        NoteDto(5, "Practising with band")
    )

    fun createLoadNoteDetailResp(): NoteDto = NoteDto(999, "Some new note")
}