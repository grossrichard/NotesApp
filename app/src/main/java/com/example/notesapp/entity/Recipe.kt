package com.example.notesapp.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Richard Gross on 2020-01-15
 */

@Entity(tableName = "recipes")
data class Recipe(

    @PrimaryKey
    var id: String,
    var name: String? = null,
    var description: String? = null,
    var duration: Int? = null,
    var ingredients: List<String>? = null,
    var info: String? = null,
    var score: Float? = null
)
