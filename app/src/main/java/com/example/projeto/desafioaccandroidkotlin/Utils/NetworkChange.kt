package com.example.projeto.desafioaccandroidkotlin.Utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.os.Build

class NetworkChange constructor(private val context: Context){

    private var network: Network? = null

    fun hasInternetConnection(): Boolean {
        val connectivity = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities: NetworkCapabilities?

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            network = connectivity.activeNetwork

            capabilities = connectivity.getNetworkCapabilities(network)

            return capabilities != null && capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
        }

        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) {
            val networks = connectivity.allNetworks
            var networkInfo: NetworkInfo
            for (mNetwork in networks) {
                networkInfo = connectivity.getNetworkInfo(mNetwork)
                if (networkInfo.detailedState == NetworkInfo.DetailedState.CONNECTED) {
                    return true
                }
            }
        } else {
            val info = connectivity.activeNetworkInfo
            if (info != null)
                if (info.detailedState == NetworkInfo.DetailedState.CONNECTED)
                    return true

        }
        return false
    }
}