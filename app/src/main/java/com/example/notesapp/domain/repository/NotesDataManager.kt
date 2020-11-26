package com.example.notesapp.domain.repository

import com.example.notesapp.infrastructure.api.Mapper
import com.example.notesapp.data.entity.EmptyDto
import com.example.notesapp.infrastructure.api.NotesApiDefinition
import com.example.notesapp.infrastructure.db.AppDatabase
import com.example.notesapp.presentation.entity.NoteFace
import com.example.notesapp.skeleton.mvvm.BaseDataManager
import io.reactivex.Single

/**
 * Created by Richard Gross on 2020-01-18
 */

class NotesDataManager(
    private val api: NotesApiDefinition,
    private val db: AppDatabase
) :
    BaseDataManager() {

    fun loadNotes(): Single<List<NoteFace>> =
        api.loadNotes()
            .map { Mapper.convert(it) }
//            .doOnSuccess { db.noteDao().addAll(it) }
//            .onErrorResumeNext { Single.just(db.noteDao().getAll()) }

    fun createNote(note: NoteFace): Single<NoteFace> =
        api.createNote(Mapper.convert(note)).map { Mapper.convert(it) }

    fun loadNoteDetail(id: Long): Single<NoteFace> =
        api.loadNoteDetail(id).map { Mapper.convert(it) }
//            .doOnSuccess { db.noteDao().addRecipeDetail(it) }
//            .onErrorResumeNext { Single.just(db.recipeDetailDao().getRecipeDetail(id)) }

    fun updateNote(id: Long, note: NoteFace): Single<NoteFace> =
        api.updateNote(id, Mapper.convert(note)).map { Mapper.convert(it) }

    fun deleteNote(id: Long): Single<EmptyDto> =
        api.deleteNote(id).map { it.body() }

}