package com.example.notesapp.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Richard Gross on 2020-01-15
 */
@Entity(tableName = "ratings")
data class Rating(
    @PrimaryKey
    var id: String,
    var score: Int? = null,
    var recipe: String? = null
)