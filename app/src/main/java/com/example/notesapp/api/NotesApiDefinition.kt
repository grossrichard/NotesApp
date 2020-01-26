package com.example.notesapp.api

import com.example.notesapp.api.dto.EmptyDto
import com.example.notesapp.api.dto.NoteDto
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.*

/**
 * Created by Richard Gross on 2020-01-18
 */
interface NotesApiDefinition {

    @GET("notes")
    fun loadNotes(): Single<List<NoteDto>>

    @GET("notes/{recipeId}")
    fun loadNoteDetail(@Path("recipeId") id: String): Single<NoteDto>

    @POST("notes")
    fun createNote(@Body note: NoteDto): Single<NoteDto>

    @PUT("notes/{recipeId}")
    fun updateNote(@Path("recipeId") id: String, @Body note: NoteDto): Single<NoteDto>

    @DELETE("notes/{recipeId}")
    fun deleteNote(@Path("recipeId") id: String): Single<Response<EmptyDto>>
}