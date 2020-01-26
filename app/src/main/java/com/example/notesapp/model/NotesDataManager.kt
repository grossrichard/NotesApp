package com.example.notesapp.model

import com.example.notesapp.api.Converter
import com.example.notesapp.api.RecipeApiService
import com.example.notesapp.db.AppDatabase
import com.example.notesapp.entity.Note
import com.example.notesapp.skeleton.mvvm.BaseDataManager
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Richard Gross on 2020-01-18
 */

@Singleton
class NotesDataManager @Inject constructor(
    private val apiService: RecipeApiService,
    private val db: AppDatabase
) :
    BaseDataManager() {

    fun loadNotes(): Single<List<Note>> =
        apiService.service.loadNotes()
            .map { Converter.convert(it) }
            .doOnSuccess { db.noteDao().addAll(it) }
            .onErrorResumeNext { Single.just(db.noteDao().getAll()) }

    fun addNote(note: Note): Single<Note> =
        apiService.service.addNote(Converter.convert(note)).map { Converter.convert(it) }

    fun loadNoteDetail(id: String): Single<Note> =
        apiService.service.loadNoteDetail(id)
            .map { Converter.convert(it) }
//            .doOnSuccess { db.noteDao().addRecipeDetail(it) }
//            .onErrorResumeNext { Single.just(db.recipeDetailDao().getRecipeDetail(id)) }

    //todo how to implement?
    fun updateNote(id: String, note: Note): Single<Note> =
        apiService.service.updateNote(id, Converter.convert(note)).map { Converter.convert(it) }

    //todo how to implement?
    fun deleteNote(id: String) = apiService.service.deleteNote(id)
}