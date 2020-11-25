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
    fun convertIngredients(ingredients: List<String>?): String? =
        ingredients?.let { GSON.toJson(it) }

    @JvmStatic
    @TypeConverter
    fun convertIngredients(ingredients: String?): List<String>? =
        ingredients?.let { GSON.fromJson(it, object : TypeToken<ArrayList<String>>() {}.type) }
}