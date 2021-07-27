package com.anand.checklocationapp.presentation.utility

import java.text.SimpleDateFormat
import java.util.*

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class Helper {
    companion object {

        /**
         * Return date in specified format.
         * @param milliSeconds Date in milliseconds
         * @param dateFormat Date format
         * @return String representing date in specified format
         */
        fun getDate(milliSeconds: Long, dateFormat: String?): String? {
            val formatter = SimpleDateFormat(dateFormat)

            // Create a calendar object that will convert the date and time value in milliseconds to date.
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = milliSeconds
            return formatter.format(calendar.time)
        }

    }
}