package com.example.notesapp.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.example.notesapp.Application

/**
 * Created by Richard Gross on 2020-01-25
 */
object NetworkUtils {

    fun checkNetworkAvailability(): Boolean {
        val cm =
            Application.instance.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo

        return activeNetwork?.isConnectedOrConnecting == true
    }

}