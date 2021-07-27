package com.anand.checklocationapp.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Anand A <anandabktda@gmail.com>
 * Defined location table
 * */

@Entity(tableName = "location")
data class LocationListData(
    @PrimaryKey(autoGenerate = true)
    val id: Long?,
    var latitude: Double? = null,
    var longitude: Double? = null,
    var time: Long? = null
)