package com.anand.checklocationapp.presentation.utility

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import com.anand.checklocationapp.presentation.utility.AppConstants.Companion.LOCATION_REFRESH_DISTANCE
import com.anand.checklocationapp.presentation.utility.AppConstants.Companion.LOCATION_REFRESH_TIME

class LocationHelper {

    // flag for GPS status
    var isGPSEnabled = false

    // flag for network status
    var isNetworkEnabled = false

    @SuppressLint("MissingPermission")
    fun startListeningUserLocation(context: Context, myListener: MyLocationListener) {

        val mLocationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager

        val locationListener: LocationListener = object : LocationListener {
            override fun onLocationChanged(location: Location) {
                myListener.onLocationChanged(location) // calling listener to inform that updated location is available
            }

            override fun onProviderEnabled(provider: String) {}
            override fun onProviderDisabled(provider: String) {}
            override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
        }

        // getting GPS status
        isGPSEnabled = mLocationManager!!
            .isProviderEnabled(LocationManager.GPS_PROVIDER)

        // getting network status
        isNetworkEnabled = mLocationManager!!
            .isProviderEnabled(LocationManager.NETWORK_PROVIDER)

        if (isGPSEnabled)
            mLocationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                LOCATION_REFRESH_TIME.toLong(),
                LOCATION_REFRESH_DISTANCE.toFloat(),
                locationListener
            )

        else if (isNetworkEnabled)
            mLocationManager.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER,
                LOCATION_REFRESH_TIME.toLong(),
                LOCATION_REFRESH_DISTANCE.toFloat(),
                locationListener
            )
    }
}

interface MyLocationListener {
    fun onLocationChanged(location: Location?)
}