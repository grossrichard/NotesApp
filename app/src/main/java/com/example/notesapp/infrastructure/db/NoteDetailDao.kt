package com.example.notesapp.infrastructure.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.notesapp.presentation.entity.NoteFace

/**
 * Created by Richard Gross on 2020-01-25
 */

@Dao
interface NoteDetailDao {

    @Query("SELECT * FROM notes WHERE id==:id")
    fun getNoteDetail(id: String): NoteFace?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addNoteDetail(note: NoteFace)
}