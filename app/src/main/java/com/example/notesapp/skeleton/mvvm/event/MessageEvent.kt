package com.example.notesapp.skeleton.mvvm.event

import androidx.annotation.StringRes

/**
 * Created by Richard Gross on 2020-01-26
 */
class MessageEvent(@StringRes val messageRes: Int) : LiveEvent() {
}