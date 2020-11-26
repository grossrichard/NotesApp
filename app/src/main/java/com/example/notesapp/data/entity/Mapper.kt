package com.example.notesapp.data.entity

import com.example.notesapp.domain.entity.NoteBdo
import com.example.notesapp.presentation.entity.NoteFace

/**
 * @author richardgross on 26/11/2020
 */
object Mapper {
    fun NoteBdo.toNoteDto() = NoteDto(id = id, title = title)
    fun NoteDto.toNoteBdo() = NoteBdo(id = id, title = title)
}