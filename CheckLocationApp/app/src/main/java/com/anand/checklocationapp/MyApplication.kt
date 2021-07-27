package com.anand.checklocationapp

import android.app.Application
import android.content.ContextWrapper
import com.anand.checklocationapp.di.dataSourceModule
import com.anand.checklocationapp.di.repositoryModule
import com.anand.checklocationapp.di.roomModule
import com.anand.checklocationapp.di.viewModelModule
import com.pixplicity.easyprefs.library.Prefs
import org.koin.android.ext.android.startKoin

open class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        init()
    }

    private fun init() {
        startKoin(
            this, arrayListOf(
                repositoryModule,
                dataSourceModule,
                viewModelModule,
                roomModule
            )
        )

        Prefs.Builder()
            .setContext(this)
            .setMode(ContextWrapper.MODE_PRIVATE)
            .setPrefsName(packageName)
            .setUseDefaultSharedPreference(true)
            .build()

    }
}