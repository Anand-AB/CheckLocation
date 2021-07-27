package com.anand.checklocationapp.di

import com.anand.checklocationapp.data.datasource.CommonApiLocalDataSource
import com.anand.checklocationapp.data.ds.CommonApiLocalDS
import org.koin.dsl.module.module

val dataSourceModule = module {
    single<CommonApiLocalDataSource> { CommonApiLocalDS(get()) }
}