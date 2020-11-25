package com.example.notesapp.infrastructure.api

import com.example.notesapp.infrastructure.api.dto.NoteDto

/**
 * Created by Richard Gross on 2020-01-19
 */
object MockDataProvider {

    fun createMockLoadRecipesResp(): List<NoteDto> =
        arrayListOf(
            NoteDto().apply {
                id = "1"
                title = "Jogging in park"
            }, NoteDto().apply {
                id = "2"
                title = "Pick-up posters from post-office"
            }, NoteDto().apply {
                id = "3"
                title = "Gym"
            }, NoteDto().apply {
                id = "4"
                title = "Gym"
            }, NoteDto().apply {
                id = "5"
                title = "Practising with band"
            })

    fun createLoadRecipeDetailResp(): NoteDto =
        NoteDto().apply {
            id = "9999"
            title = "Some new note"
        }
}