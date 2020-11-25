package com.example.notesapp.presentation.util

import android.content.Context
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import com.example.notesapp.R

/**
 * Created by Richard Gross on 2020-01-25
 */
object AlertDialogCreator {

    fun createDefaultErrorDialog(context: Context) {
        AlertDialog.Builder(context).apply {
            setMessage(UiUtils.getString(R.string.dialog_error_msg))
            setTitle(UiUtils.getString(R.string.dialog_error_title))
            setCancelable(false)
            setNeutralButton(
                UiUtils.getString(R.string.dialog_ok)
            ) { dialog, _ -> dialog.cancel() }
        }.create()
            .show()
    }

    fun createMessageDialog(context: Context, @StringRes messageRes: Int) {
        AlertDialog.Builder(context).apply {
            setTitle(UiUtils.getString(messageRes))
            setCancelable(false)
            setNeutralButton(
                UiUtils.getString(R.string.dialog_ok)
            ) { dialog, _ -> dialog.cancel() }
        }.create()
            .show()
    }

    fun createNoInternetDialog(context: Context) {
        AlertDialog.Builder(context).apply {
            setMessage(UiUtils.getString(R.string.dialog_error_msg))
            setTitle(UiUtils.getString(R.string.dialog_no_internet_available))
            setCancelable(false)
            setNeutralButton(
                UiUtils.getString(R.string.dialog_ok)
            ) { dialog, _ -> dialog.cancel() }
                .create()
                .show()
        }
    }
}