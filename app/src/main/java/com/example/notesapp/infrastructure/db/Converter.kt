package com.example.notesapp.infrastructure.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Created by Richard Gross on 2020-01-25
 */


object Converter {

    private val GSON = Gson()

    @JvmStatic
    @TypeConverter
    fun convertNotes(notes: List<String>?): String? = notes?.let { GSON.toJson(it) }

    @JvmStatic
    @TypeConverter
    fun convertNote(note: String?): List<String>? =
        note?.let { GSON.fromJson(it, object : TypeToken<ArrayList<String>>() {}.type) }
}