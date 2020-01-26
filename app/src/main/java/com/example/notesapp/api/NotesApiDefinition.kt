package com.example.notesapp.api

import com.example.notesapp.api.dto.NoteDto
import io.reactivex.Single
import retrofit2.http.*

/**
 * Created by Richard Gross on 2020-01-18
 */
interface NotesApiDefinition {

    @GET("notes")
    fun loadNotes(): Single<List<NoteDto>>

    @POST("notes")
    fun addNote(@Body note: NoteDto): Single<NoteDto>

    @PUT("notes/{recipeId}")
    fun updateNote(@Path("recipeId") id: String, note: NoteDto): Single<NoteDto>

    @DELETE("notes/{recipeId}")
    fun deleteNote(@Path("recipeId") id: String)

    @GET("notes/{recipeId}")
    fun loadNoteDetail(@Path("recipeId") id: String): Single<NoteDto>
}