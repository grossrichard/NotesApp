package com.example.notesapp.util

import android.graphics.drawable.Drawable
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import com.example.notesapp.Application

/**
 * Created by Richard Gross on 2020-01-24
 */
object UiUtils {

    fun getColor(@ColorRes colorRes: Int): Int {
        return ContextCompat.getColor(Application.instance, colorRes)
    }

    fun getDrawable(@DrawableRes drawableRes: Int): Drawable? {
        return ContextCompat.getDrawable(Application.instance, drawableRes)
    }


    fun getString(@StringRes stringRes: Int): String? {
        return Application.instance.getString(stringRes)
    }

}