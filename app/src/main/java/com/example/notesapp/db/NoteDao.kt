package com.example.notesapp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.notesapp.entity.Note

/**
 * Created by Richard Gross on 2020-01-25
 */

@Dao
interface NoteDao {

    @Query("SELECT * FROM notes")
    fun getAll(): List<Note>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAll(notes: List<Note>)
}