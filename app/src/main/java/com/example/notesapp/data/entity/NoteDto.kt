package com.example.notesapp.data.entity

import com.google.gson.annotations.SerializedName

/**
 * Created by Richard Gross on 2020-01-15
 */
data class NoteDto(
    @SerializedName("id") val id: Long? = null,
    @SerializedName("title") val title: String? = null
)