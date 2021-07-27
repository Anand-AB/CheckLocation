package com.anand.checklocationapp.data.database

import androidx.room.Dao
import androidx.room.Insert
import com.anand.checklocationapp.data.models.LocationListData

/**
 * Created by Anand A <anandabktda@gmail.com>
 * The Dao helps to do data base operations
 * */

@Dao
interface CheckLocationDao {

    @androidx.room.Query("SELECT * FROM location ")
    fun getLocationListSaved(): List<LocationListData>

    @Insert
    fun insertLocation(location: LocationListData)

}