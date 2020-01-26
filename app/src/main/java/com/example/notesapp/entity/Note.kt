package com.example.notesapp.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Richard Gross on 2020-01-15
 */

@Entity(tableName = "notes")
data class Note(

    @PrimaryKey
    var id: String,
    var title: String? = null
)
