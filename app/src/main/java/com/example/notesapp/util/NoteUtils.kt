package com.example.notesapp.util

import androidx.annotation.MenuRes
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

    @JvmStatic
    fun isEditable(mode: NoteDetailMode): Boolean =
        when (mode) {
            READ, DELETE -> false
            EDIT, CREATE -> true
        }

//    @JvmStatic
//    @MenuRes
//    fun getNoteDetailMenu(mode: NoteDetailMode): Int =
//        when (mode) {
//            READ -> R.menu.menu_detail
//            EDIT -> R.menu.menu_detail_edit
//            CREATE -> R.menu.menu_confirm
//            DELETE -> R.menu.menu_detail_delete
//        }
}
