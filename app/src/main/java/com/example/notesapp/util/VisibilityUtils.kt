package com.example.notesapp.util

import android.view.View

/**
 * Created by richardgross on 2019-10-07
 */
object VisibilityUtils {

    fun visibleOrGone(visible: Boolean): Int = if (visible) View.VISIBLE else View.GONE

    fun visibleOrGone(view: View, visible: Boolean) {
        view.visibility = visibleOrGone(visible)
    }

    fun visibleOrInvisible(visible: Boolean): Int = if (visible) View.VISIBLE else View.INVISIBLE

    fun visibleOrInvisible(view: View, visible: Boolean) {
        view.visibility = visibleOrInvisible(visible)
    }

}