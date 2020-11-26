package com.example.notesapp.infrastructure.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.notesapp.data.entity.NoteDbo
import com.example.notesapp.presentation.entity.NoteFace

/**
 * Created by Richard Gross on 2020-01-25
 */

@Database(entities = [NoteDbo::class], version = 1)
@TypeConverters(Converter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao
}