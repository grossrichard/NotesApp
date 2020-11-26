package com.example.notesapp.infrastructure.api

import com.example.notesapp.infrastructure.api.dto.EmptyDto
import com.example.notesapp.infrastructure.api.dto.NoteDto
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.*

/**
 * Created by Richard Gross on 2020-01-18
 */
interface NotesApiDefinition {

    @GET("notes")
    fun loadNotes(): Single<List<NoteDto>>

    @GET("notes/{noteId}")
    fun loadNoteDetail(@Path("noteId") id: String): Single<NoteDto>

    @POST("notes")
    fun createNote(@Body note: NoteDto): Single<NoteDto>

    @PUT("notes/{noteId}")
    fun updateNote(@Path("noteId") id: String, @Body note: NoteDto): Single<NoteDto>

    @DELETE("notes/{noteId}")
    fun deleteNote(@Path("noteId") id: String): Single<Response<EmptyDto>>
}