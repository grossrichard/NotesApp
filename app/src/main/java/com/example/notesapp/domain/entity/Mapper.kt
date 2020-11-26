package com.example.notesapp.domain.entity

import com.example.notesapp.presentation.entity.NoteFace

/**
 * @author richardgross on 26/11/2020
 */
object Mapper {
    fun NoteBdo.toNoteFace() = NoteFace(id = id, title = title)
    fun NoteFace.toNoteBdo() = NoteBdo(id = id, title = title)
}