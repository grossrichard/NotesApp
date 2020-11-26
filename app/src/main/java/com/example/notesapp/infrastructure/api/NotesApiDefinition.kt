package com.example.notesapp.infrastructure.api

import com.example.notesapp.data.entity.EmptyDto
import com.example.notesapp.data.entity.NoteDto
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
    fun loadNoteDetail(@Path("noteId") id: Long): Single<NoteDto>

    @POST("notes")
    fun createNote(@Body note: NoteDto): Single<NoteDto>

    @PUT("notes/{noteId}")
    fun updateNote(@Path("noteId") id: Long, @Body note: NoteDto): Single<NoteDto>

    @DELETE("notes/{noteId}")
    fun deleteNote(@Path("noteId") id: Long): Single<Response<EmptyDto>>
}