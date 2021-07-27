package com.anand.checklocationapp.data.ds

import com.anand.checklocationapp.data.BaseRepository
import com.anand.checklocationapp.data.database.CheckLocationDao
import com.anand.checklocationapp.data.datasource.CommonApiLocalDataSource
import com.anand.checklocationapp.data.models.LocationListData

class CommonApiLocalDS constructor(private val checkLocationDao: CheckLocationDao) :
    BaseRepository(),
    CommonApiLocalDataSource {

    override suspend fun getLocationListSaved(): List<LocationListData> {
        return checkLocationDao.getLocationListSaved()
    }

}