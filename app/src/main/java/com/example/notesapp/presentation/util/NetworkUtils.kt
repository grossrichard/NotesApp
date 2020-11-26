package com.example.notesapp.presentation.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.example.notesapp.App

/**
 * Created by Richard Gross on 2020-01-25
 */
object NetworkUtils {

    fun checkNetworkAvailability(): Boolean {
        val cm =
            App.instance.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo

        return activeNetwork?.isConnectedOrConnecting == true
    }

}