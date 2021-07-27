package com.anand.checklocationapp.data.datasource

import com.anand.checklocationapp.data.models.LocationListData

interface CommonApiLocalDataSource {

    suspend fun getLocationListSaved(): List<LocationListData>

}