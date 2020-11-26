package com.example.notesapp.infrastructure.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.notesapp.entity.Note

/**
 * Created by Richard Gross on 2020-01-25
 */

@Dao
interface NoteDetailDao {

    @Query("SELECT * FROM notes WHERE id==:id")
    fun getNoteDetail(id: String): Note?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addNoteDetail(note: Note)
}