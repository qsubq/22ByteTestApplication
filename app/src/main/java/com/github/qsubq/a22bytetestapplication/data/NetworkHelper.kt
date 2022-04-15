@file:Suppress("DEPRECATION")

package com.github.qsubq.a22bytetestapplication.data

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import android.util.Log.INFO
import okhttp3.internal.platform.Platform.INFO
import java.util.logging.Level.INFO

class NetworkHelper(){
    companion object{
        fun isOnline(context: Context): Boolean {
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
                    || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                    || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    Log.i("Internet", "Device is using network connection")
                    return true
                } else {
                    Log.i("Internet", "No network connection")
                }
            }
            return false
        }
    }
}