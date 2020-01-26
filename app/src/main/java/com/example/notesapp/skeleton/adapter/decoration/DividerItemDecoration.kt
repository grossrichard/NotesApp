package com.example.notesapp.skeleton.adapter.decoration

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.view.View
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.Application

class DividerItemDecoration(context: Context, @DrawableRes dividerId: Int) :
    RecyclerView.ItemDecoration() {
    private val mDivider: Drawable = ContextCompat.getDrawable(Application.instance, dividerId)!!

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)

        val left: Int = parent.paddingLeft
        val right: Int = parent.width - parent.paddingRight
        val childCount: Int = parent.childCount
        // childCount - 1 → because we don't want divider_settings on last item
        (0 until childCount - 1).forEach { i ->
            val child: View = parent.getChildAt(i)
            val params: RecyclerView.LayoutParams =
                child.layoutParams as RecyclerView.LayoutParams
            val top: Int = child.bottom + params.bottomMargin
            val bottom = top + mDivider.intrinsicHeight
            mDivider.setBounds(left, top, right, bottom)
            mDivider.draw(c)
        }
    }

}