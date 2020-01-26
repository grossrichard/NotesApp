package com.example.notesapp.view.bindings

import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.text.Spannable
import android.text.SpannableString
import android.text.Spanned
import android.text.style.BulletSpan
import android.text.style.ImageSpan
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.EditText
import android.widget.RatingBar
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.example.notesapp.Application
import com.example.notesapp.util.UiUtils


@BindingAdapter("backgroundResource")
fun setBackgroundResource(view: View, resId: Int?) {
    if (resId != null) {
        view.setBackgroundResource(resId)
    } else {
        view.background = null
    }
}

@BindingAdapter("bgViewGroupResource")
fun setBackgroundResource(view: ViewGroup, resId: Int) {
    view.setBackgroundResource(resId)
}

@BindingAdapter("textColorResource")
fun setTextColor(editText: EditText, resId: Int?) {
    resId?.let { editText.setTextColor(ContextCompat.getColor(Application.instance, it)) }
}

@BindingAdapter("textColorResource")
fun setTextColor(textView: TextView, resId: Int?) {
    resId?.let { textView.setTextColor(ContextCompat.getColor(Application.instance, it)) }
}

@BindingAdapter("visible_or_gone")
fun setVisibleOrGone(view: View, visible: Boolean?) {
    view.visibility = if (visible == true) View.VISIBLE else View.GONE
}

@BindingAdapter("visible_or_invisible")
fun setVisibleOrInvisible(view: View, visible: Boolean?) {
    view.visibility = if (visible == true) View.VISIBLE else View.INVISIBLE
}

@BindingAdapter("text_or_gone")
fun setTextOrGone(textView: TextView, stringRes: Int?) {
    if (stringRes == null || stringRes == 0) {
        textView.visibility = View.GONE
    } else {
        textView.visibility = View.VISIBLE
        textView.text = Application.instance.resources.getString(stringRes)
    }
}

@BindingAdapter("text_or_invisible")
fun setTextOrInvisible(textView: TextView, stringRes: Int?) {
    if (stringRes == null || stringRes == 0) {
        textView.visibility = View.INVISIBLE
    } else {
        textView.visibility = View.VISIBLE
        textView.text = Application.instance.resources.getString(stringRes)
    }
}

@BindingAdapter("selected", requireAll = false)
fun setSelected(view: View, selected: Boolean?) {
    view.isSelected = selected == true
}

@BindingAdapter("width")
fun setWidth(view: View, width: Int) {
    val lp = view.layoutParams
    lp.width = width

    view.layoutParams = lp
}

@BindingAdapter("width")
fun setWidth(view: View, width: Float) {
    val lp = view.layoutParams
    lp.width = width.toInt()

    view.layoutParams = lp
}

@BindingAdapter("padding")
fun setPadding(view: View, paddingRes: Int) {
    val padding = Application.instance.resources.getDimension(paddingRes).toInt()
    view.setPadding(padding, padding, padding, padding)
}

@BindingAdapter("onFocusChange")
fun onFocusChange(text: EditText, listener: View.OnFocusChangeListener?) {
    listener?.let {
        text.onFocusChangeListener = listener
    }
}

@BindingAdapter("onKeyListener")
fun onFocusChange(et: EditText, listener: View.OnKeyListener?) {
    listener?.let {
        et.setOnKeyListener(listener)
    }
}

@BindingAdapter("requestFocus")
fun requestFocus(view: TextView, requestFocus: Boolean?) {
    if (requestFocus == true) {
        view.isFocusableInTouchMode = true
        view.requestFocus()
    }
}


@BindingAdapter("left_ImageSpan")
fun leftImageSpan(textView: TextView, @DrawableRes imageRes: Int) {
    val spannable = SpannableString("  " + textView.text)
    val drawable = ContextCompat.getDrawable(Application.instance, imageRes)
    drawable?.let {
        drawable.setBounds(0, 0, textView.lineHeight, textView.lineHeight)
        val imageSpan = ImageSpan(drawable, ImageSpan.ALIGN_BOTTOM)
        spannable.setSpan(imageSpan, 0, 1, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
    }

    textView.text = spannable
}

@BindingAdapter("right_ImageSpan")
fun rightImageSpan(textView: TextView, @DrawableRes imageRes: Int) {
    val spannable = SpannableString(StringBuilder(textView.text).append("  "))
    val drawable = ContextCompat.getDrawable(Application.instance, imageRes)
    drawable?.let {
        drawable.setBounds(0, 0, textView.lineHeight, textView.lineHeight)
        val imageSpan = ImageSpan(drawable, ImageSpan.ALIGN_BOTTOM)
        spannable.setSpan(
            imageSpan,
            spannable.length - 1,
            spannable.length,
            Spannable.SPAN_INCLUSIVE_EXCLUSIVE
        )
    }

    textView.text = spannable
}


@BindingAdapter("android:drawablePadding")
fun setDrawablePadding(view: TextView, padding: Float?) {
    padding?.let { view.compoundDrawablePadding = Math.round(it) }
}

@BindingAdapter("android:checked")
fun setChecked(checkableView: CompoundButton, isChecked: Boolean?) {
    checkableView.isChecked = isChecked == true
}

@BindingAdapter("android:enabled")
fun setEnabled(view: View, enabled: Boolean?) {
    view.isEnabled = enabled == true
}

@BindingAdapter("bullet_span_enabled", "bullet_span_space", requireAll = true)
fun setBulletSpan(view: TextView, bulletSpanEnabled: Boolean, @DimenRes space: Int) {
    if (bulletSpanEnabled) {
        val string = SpannableString(view.text)
        val spacing = view.context.resources.getDimension(space).toInt()
        string.setSpan(BulletSpan(spacing), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
    }
}

@BindingAdapter("progress_color")
fun setRatingBarProgressColor(ratingBar: RatingBar, @ColorRes colorRes: Int) {
    ratingBar.progressDrawable.colorFilter =
        PorterDuffColorFilter(UiUtils.getColor(colorRes), PorterDuff.Mode.SRC_ATOP)
}

@BindingAdapter("progress_secondary_color")
fun setRatingBarProgressSecondaryColor(ratingBar: RatingBar, @ColorRes colorRes: Int) {
    ratingBar.background.colorFilter =
        PorterDuffColorFilter(UiUtils.getColor(colorRes), PorterDuff.Mode.SRC_ATOP)
}