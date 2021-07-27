package com.anand.checklocationapp.di

import com.anand.checklocationapp.data.contract.CommonApisRepo
import com.anand.checklocationapp.data.repository.CommonApisRepository
import org.koin.dsl.module.module

val repositoryModule = module {
    single<CommonApisRepo> { CommonApisRepository(get()) }
}