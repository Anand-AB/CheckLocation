package com.anand.checklocationapp.presentation.locationList

import android.Manifest
import android.app.ActivityManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.location.LocationManager
import android.provider.Settings
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.anand.checklocationapp.R
import com.anand.checklocationapp.data.models.LocationListData
import com.anand.checklocationapp.presentation.adapter.LocationListAdapter
import com.anand.checklocationapp.presentation.core.BaseActivity
import com.anand.checklocationapp.presentation.utility.AppConstants.Companion.BROADCAST_ACTION
import com.anand.checklocationapp.presentation.utility.AppConstants.Companion.BROADCAST_TYPE
import com.anand.checklocationapp.presentation.utility.AppConstants.Companion.BROADCAST_VALUE
import com.anand.checklocationapp.presentation.utility.AppConstants.Companion.SERVICE_NAME
import com.anand.checklocationapp.presentation.utility.AppConstants.Companion.THIRTY_MINUTES
import com.anand.checklocationapp.service.LocationService
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_location_list.*
import kotlinx.android.synthetic.main.layout_toolbar_main.*
import org.koin.android.viewmodel.ext.android.viewModel


/**
 * Created by Anand A <anandabktda@gmail.com>
 * The Activity used for showing Location list
 * */

class LocationListActivity : BaseActivity() {

    private var locationListAdapter: LocationListAdapter? = null
    private var locationList: MutableList<LocationListData> = arrayListOf()

    private lateinit var locationManager: LocationManager
    var gpsStatus = false
    var intent1: Intent? = null

    override fun getLayout(): Int {
        return R.layout.activity_location_list
    }

    private val locationListViewModel: LocationListViewModel by viewModel()
    override fun getBaseViewModel() = locationListViewModel

    override fun initiation() {
        super.initiation()

        setSupportActionBar(toolbar)
        setAdapter()

        showProgress()
        locationListViewModel.getLocationListSaved()

    }

    private fun checkPermission(): Boolean {
        val result = ContextCompat.checkSelfPermission(
            applicationContext,
            Manifest.permission.ACCESS_FINE_LOCATION
        )
        val result1 = ContextCompat.checkSelfPermission(
            applicationContext,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
        return result == PackageManager.PERMISSION_GRANTED && result1 == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ),
            1
        )
    }

    private fun checkGpsStatus(): Boolean {
        locationManager =
            this@LocationListActivity.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        gpsStatus = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)

        return gpsStatus

    }

    fun gpsStatus() {
        intent1 = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        startActivity(intent1)
    }

    private fun setAdapter() {

        locationListAdapter = object : LocationListAdapter(this) {

            override fun itemOnCLick(data: LocationListData) {
                super.itemOnCLick(data)

            }
        }

        rv_location.adapter = locationListAdapter

    }

    private fun setNewsData(locationList: List<LocationListData>) {
        hideProgress()
        locationListAdapter?.clear()
        locationListAdapter?.addAll(locationList)

        rv_location.visibility = View.VISIBLE
        tv_no_data.visibility = View.GONE
    }

    private fun newsListEmpty(message: String) {
        hideProgress()
        rv_location.visibility = View.GONE
        tv_no_data.visibility = View.VISIBLE
        tv_no_data.text = message
    }

    override fun onResume() {
        super.onResume()

        if (!checkPermission()) {
            requestPermission()
        } else
            if (checkGpsStatus()) {
                if (!isServiceRunning())
                    ContextCompat.startForegroundService(
                        this,
                        Intent(this, LocationService::class.java)
                    )
            } else
                Snackbar.make(
                    rl_main,
                    R.string.turn_on_gps,
                    Snackbar.LENGTH_INDEFINITE
                )
                    .setAction(R.string.ok) {
                        gpsStatus()
                    }
                    .show()

        if (locationList.isNotEmpty())
            locationListViewModel.getLocationListSaved()

        registerReceiver(mMessageReceiver, IntentFilter(BROADCAST_ACTION))
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(mMessageReceiver)
    }

    override fun getApiResponse() {
        super.getApiResponse()

        locationListViewModel.locationListSavedLiveData.observe(this, Observer {

            hideProgress()
            if (locationList.isNotEmpty())
                locationList.clear()

            if (it.isNotEmpty()) {

                for (i in it.indices) {

                    val thirtyAgo: Long = System.currentTimeMillis() - THIRTY_MINUTES

                    if (it[i].time!! > thirtyAgo) {
                        locationList.add(it[i])
                    }
                }

                if (locationList.isNotEmpty()) {
                    setNewsData(locationList)
                } else {
                    newsListEmpty(getString(R.string.text_no_data))
                }
            } else
                newsListEmpty(getString(R.string.text_no_data))

        })

    }

    //This is the handler that will manager to process the broadcast intent. Will get the broadcast message when new Location added
    private val mMessageReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(
            context: Context?,
            intent: Intent
        ) {
            // Extract data included in the Intent
            val updateNotificationStatus = intent.getStringExtra(BROADCAST_TYPE)
            if (updateNotificationStatus == BROADCAST_VALUE) {
                locationListViewModel.getLocationListSaved()
            }

        }
    }

    private fun isServiceRunning(): Boolean {
        val manager = getSystemService(ACTIVITY_SERVICE) as ActivityManager

        for (service in manager.getRunningServices(Int.MAX_VALUE)) {
            if (SERVICE_NAME == service.service.className) {
                return true
            }
        }
        return false
    }

}
