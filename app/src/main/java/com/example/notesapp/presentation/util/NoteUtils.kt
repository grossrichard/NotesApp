package com.example.notesapp.presentation.util

import androidx.annotation.StringRes
import com.example.notesapp.R
import com.example.notesapp.entity.NoteDetailMode
import com.example.notesapp.entity.NoteDetailMode.*

/**
 * Created by Richard Gross on 2020-01-26
 */
object NoteUtils {

    @StringRes
    @JvmStatic
    fun getNoteDetailTitle(mode: NoteDetailMode): Int =
        when (mode) {
            READ -> R.string.note_detail_title_default
            EDIT -> R.string.note_detail_title_edit
            CREATE -> R.string.note_detail_title_create
            DELETE -> R.string.note_detail_title_delete
        }
}
