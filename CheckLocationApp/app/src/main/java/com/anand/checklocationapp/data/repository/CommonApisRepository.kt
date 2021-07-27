package com.anand.checklocationapp.data.repository

import com.anand.checklocationapp.data.BaseRepository
import com.anand.checklocationapp.data.Either
import com.anand.checklocationapp.data.contract.CommonApisRepo
import com.anand.checklocationapp.data.datasource.CommonApiLocalDataSource
import com.anand.checklocationapp.data.models.LocationListData
import com.anand.checklocationapp.domain.exceptions.MyException

class CommonApisRepository constructor(
    private val commonApiLocalDataSource: CommonApiLocalDataSource
) : CommonApisRepo,
    BaseRepository() {

    override suspend fun getLocationListSaved(): Either<MyException, List<LocationListData>> {
        return eitherLocal(commonApiLocalDataSource.getLocationListSaved())
    }
}