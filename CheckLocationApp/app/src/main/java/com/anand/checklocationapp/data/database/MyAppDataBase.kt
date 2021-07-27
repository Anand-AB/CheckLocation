package com.anand.checklocationapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.anand.checklocationapp.data.models.LocationListData
import com.anand.checklocationapp.presentation.utility.AppConstants.Companion.DB_NAME

/**
 * Created by Anand A <anandabktda@gmail.com>
 * The file used for database details setup
 * */

@Database(

    entities = [LocationListData::class
    ], version = 1, exportSchema = false
)

abstract class MyAppDataBase : RoomDatabase() {

    abstract fun getCheckLocationDao(): CheckLocationDao
}

object DatabaseProvider {
    private lateinit var INSTANCE: MyAppDataBase

    fun getDataBase(context: Context): MyAppDataBase {
        INSTANCE =
            Room.databaseBuilder(context.applicationContext, MyAppDataBase::class.java, DB_NAME)
                .allowMainThreadQueries()
                .build()
        return INSTANCE
    }
}