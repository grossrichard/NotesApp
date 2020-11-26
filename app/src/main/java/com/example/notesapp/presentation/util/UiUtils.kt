package com.example.notesapp.presentation.util

import android.graphics.drawable.Drawable
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import com.example.notesapp.App

/**
 * Created by Richard Gross on 2020-01-24
 */
object UiUtils {

    fun getColor(@ColorRes colorRes: Int): Int {
        return ContextCompat.getColor(App.instance, colorRes)
    }

    fun getDrawable(@DrawableRes drawableRes: Int): Drawable? {
        return ContextCompat.getDrawable(App.instance, drawableRes)
    }


    fun getString(@StringRes stringRes: Int): String? {
        return App.instance.getString(stringRes)
    }

}