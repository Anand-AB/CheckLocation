package com.anand.checklocationapp.presentation.utility

interface AppConstants {
    companion object {

        ////////////////////////////////////////// Local Store Constants /////////////////////////////////////////////////
        const val DB_NAME = "CheckLocationAppDatabase"

        ////////////////////////////////////////// Notification Constants /////////////////////////////////////////////////
        const val NOTIFICATION_CHANNEL_ID = "Location Tracking"
        const val NOTIFICATION_CHANNEL_DESC = "Allow Location Tracking"

        ////////////////////////////////////////// commonly using values //////////////////////////////////////////
        const val TIME_FORMAT = "hh:mm aa"

        const val CHIVO_REGULAR_TTF = "chivo_regular.ttf"
        const val PUBLIC_SANS_TTF = "publicsans_regular.ttf"
        const val QUEENS_PARK_TTF = "queens_park_bold.ttf"
        const val QUEENS_PARK_NORMAL_TTF = "queens_park.ttf"

        const val THIRTY_MINUTES = 30 * 60 * 1000
        const val LOCATION_REFRESH_TIME = 10 * 60 * 1000 // 5 seconds. The Minimum Time to get location update
        //const val LOCATION_REFRESH_TIME = 5000 // Test 5 seconds. The Minimum Time to get location update
        const val LOCATION_REFRESH_DISTANCE =0 // 0 meters. The Minimum Distance to be changed to get location update

        const val BROADCAST_ACTION = "com.anand.checklocationapp"
        const val SERVICE_NAME = "com.anand.checklocationapp.service.LocationService"
        const val BROADCAST_TYPE = "updateLocation"
        const val BROADCAST_VALUE = "1"

    }
}