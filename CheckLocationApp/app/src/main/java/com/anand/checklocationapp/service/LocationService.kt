package com.anand.checklocationapp.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.location.Location
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.anand.checklocationapp.R
import com.anand.checklocationapp.data.database.DatabaseProvider
import com.anand.checklocationapp.data.models.LocationListData
import com.anand.checklocationapp.presentation.utility.AppConstants.Companion.BROADCAST_ACTION
import com.anand.checklocationapp.presentation.utility.AppConstants.Companion.BROADCAST_TYPE
import com.anand.checklocationapp.presentation.utility.AppConstants.Companion.BROADCAST_VALUE
import com.anand.checklocationapp.presentation.utility.AppConstants.Companion.NOTIFICATION_CHANNEL_DESC
import com.anand.checklocationapp.presentation.utility.AppConstants.Companion.NOTIFICATION_CHANNEL_ID
import com.anand.checklocationapp.presentation.utility.LocationHelper
import com.anand.checklocationapp.presentation.utility.MyLocationListener
import java.util.*

class LocationService : Service() {

    override fun onCreate() {
        super.onCreate()

        isServiceStarted = true

        val builder: NotificationCompat.Builder =
            NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
                .setOngoing(false)
                .setContentTitle(getString(R.string.app_name))
                .setContentText(getString(R.string.text_fetch_location))
                .setSmallIcon(R.drawable.ic_noti)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationManager: NotificationManager =
                getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            val notificationChannel = NotificationChannel(
                NOTIFICATION_CHANNEL_ID,
                NOTIFICATION_CHANNEL_ID, NotificationManager.IMPORTANCE_LOW
            )
            notificationChannel.description = NOTIFICATION_CHANNEL_DESC
            notificationChannel.setSound(null, null)
            notificationManager.createNotificationChannel(notificationChannel)
            startForeground(1, builder.build())
        }
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {

        LocationHelper().startListeningUserLocation(
            this, object : MyLocationListener {
                override fun onLocationChanged(location: Location?) {
                    mLocation = location

                    val locationListData = LocationListData(
                        System.currentTimeMillis(),
                        mLocation!!.latitude,
                        mLocation!!.longitude,
                        System.currentTimeMillis()
                    )

                    DatabaseProvider.getDataBase(this@LocationService).getCheckLocationDao()
                        .insertLocation(locationListData)

                    val intent1 = Intent(BROADCAST_ACTION)
                    intent1.putExtra(BROADCAST_TYPE, BROADCAST_VALUE)
                    this@LocationService.sendBroadcast(intent1)

                }
            })
        return START_STICKY
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
        isServiceStarted = false

    }

    companion object {
        var mLocation: Location? = null
        var isServiceStarted = false
    }
}