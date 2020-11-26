package com.example.notesapp.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author richardgross on 26/11/2020
 */

@Entity
data class NoteDbo(
    @PrimaryKey val id: Long,
    @ColumnInfo(name = "title") val title: String? = null
)