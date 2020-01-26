package com.example.notesapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.notesapp.entity.Note

/**
 * Created by Richard Gross on 2020-01-25
 */

@Database(entities = [Note::class], version = 1)
@TypeConverters(Converter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao
}